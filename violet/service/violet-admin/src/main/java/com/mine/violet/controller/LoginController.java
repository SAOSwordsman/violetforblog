package com.mine.violet.controller;


import com.mine.violet.commonutils.JwtUtils;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.User;
import com.mine.violet.entity.vo.LoginVo;
import com.mine.violet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/adminservice/user")
public class LoginController {

    @Autowired
    private UserService userService;

//    //login
//    @PostMapping("/login")
//    public R login(){
//        return R.ok().data("token","admin");
//    }
//
//    info
    @GetMapping("/info/{token}")
    public R info(@PathVariable String  token){
        //调用jwt,返回用户id
        System.out.println(token);
        String userId = JwtUtils.getUserIdByJwtToken(token);
        User user = userService.getById(userId);
        return R.ok().data("roles","[admin]").data("name",user.getUserName()).data("avatar",user.getAvatar());
    }

    //login
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        System.out.println("======");
        System.out.println(loginVo.toString());
        String token = userService.login(loginVo);
        return R.ok().data("token",token);
    }

    @PostMapping("/logout")
    public R login(){
        return R.ok();
    }

    //info
//    @GetMapping("/info")
//    public R info(@RequestBody String userName){
//        User user = userService.getById(userName);
//        return R.ok().data("name",user.getUserName()).data("avatar",user.getAvatar());
//    }

}
