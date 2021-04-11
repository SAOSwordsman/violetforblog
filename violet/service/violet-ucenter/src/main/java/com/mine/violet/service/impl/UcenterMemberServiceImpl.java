package com.mine.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mine.violet.commonutils.JwtUtils;
import com.mine.violet.commonutils.MD5;
import com.mine.violet.entity.UcenterMember;
import com.mine.violet.entity.vo.LoginVo;
import com.mine.violet.entity.vo.RegisterVo;
import com.mine.violet.exceptionhandler.VioletException;
import com.mine.violet.mapper.UcenterMemberMapper;
import com.mine.violet.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author violet
 * @since 2020-08-25
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate redisTemplate;

    //逻辑删除
    @Override
    public void deleteById(String id) {
        UcenterMember ucenterMember = baseMapper.selectById(id);
        int deleteId=0;
        if(ucenterMember.getIsDeleted()==0){
            deleteId=1;
            baseMapper.updateId(id,deleteId);
        }else{
            deleteId=0;
            baseMapper.updateId(id,deleteId);
        }
    }

    //登录
    @Override
    public String login(LoginVo loginVo) {
        //获取登录手机号和密码
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //手机号和密码非空判断
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
            throw new VioletException(20001,"登录失败");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);

        //判断查出来的对象是否为空
        if(mobileMember == null){
            throw new VioletException(20001,"手机号不存在");
        }

        //判断密码是否正确
        //加密方式是 MD5
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new VioletException(20001,"密码不正确");
        }

        //判断用户是否禁用
        if(mobileMember.getIsDeleted()==1){
            throw new VioletException(20001,"此用户被禁用");
        }

        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(),mobileMember.getNickname());

        return jwtToken;
    }

    //注册
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();

        //非空判断
        if(StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)
                ||StringUtils.isEmpty(code)){
            throw new VioletException(20001,"登录失败");
        }

        //判断验证码，获取redis中的
        String redisCode = redisTemplate.opsForValue().get(mobile)+"";

        if(!code.equals(redisCode)){
            throw new VioletException(20001,"注册失败");
        }

        //判断手机号是否重复，表里面存在相同手机号不添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0){
            throw new VioletException(20001,"注册失败");
        }

        //数据添加到数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword((MD5.encrypt(password)));
        member.setAvatar("http://img.violet-nian.top/imgs/2020/07/d8d8aa0a70741e67.png");
        baseMapper.insert(member);

    }

    //查询某一天的注册人数
    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }

}
