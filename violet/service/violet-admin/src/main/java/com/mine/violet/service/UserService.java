package com.mine.violet.service;

import com.mine.violet.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mine.violet.entity.vo.LoginVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author violet
 * @since 2020-11-14
 */
public interface UserService extends IService<User> {

    String login(LoginVo loginVo);

}
