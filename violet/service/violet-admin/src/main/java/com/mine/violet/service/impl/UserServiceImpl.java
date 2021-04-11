package com.mine.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mine.violet.commonutils.JwtUtils;
import com.mine.violet.commonutils.MD5;
import com.mine.violet.entity.User;
import com.mine.violet.entity.vo.LoginVo;
import com.mine.violet.exceptionhandler.VioletException;
import com.mine.violet.mapper.UserMapper;
import com.mine.violet.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author violet
 * @since 2020-11-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public String login(LoginVo loginVo) {
        //获取登录手机号和密码
        String userName = loginVo.getUserName();
        String password = loginVo.getPassword();

        //手机号和密码非空判断
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            throw new VioletException(20001,"登录失败");
        }

        //判断手机号是否正确
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName);
        User user = baseMapper.selectOne(wrapper);

        //判断查出来的对象是否为空
        if(user == null){
            throw new VioletException(20001,"账号不存在");
        }

        //判断密码是否正确
        //加密方式是 MD5
        if(!MD5.encrypt(password).equals(user.getPassword())){
            throw new VioletException(20001,"密码不正确");
        }


        String jwtToken = JwtUtils.getJwtToken(user.getId(),user.getUserName());

        return jwtToken;
    }
}
