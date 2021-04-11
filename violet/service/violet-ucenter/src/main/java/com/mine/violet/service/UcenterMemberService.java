package com.mine.violet.service;

import com.mine.violet.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mine.violet.entity.vo.LoginVo;
import com.mine.violet.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author violet
 * @since 2020-08-25
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //逻辑删除
    void deleteById(String id);

    //登录
    String login(LoginVo loginVo);

    //注册
    void register(RegisterVo registerVo);

    //查询某一天注册人数
    Integer countRegisterDay(String day);
}
