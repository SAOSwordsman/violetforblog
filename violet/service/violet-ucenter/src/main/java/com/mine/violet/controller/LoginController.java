package com.mine.violet.controller;


import com.mine.violet.commonutils.JwtUtils;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.UcenterMember;
import com.mine.violet.entity.vo.LoginVo;
import com.mine.violet.entity.vo.RegisterVo;
import com.mine.violet.service.UcenterMemberService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/violetcenter/user")
public class LoginController {


    @Autowired
    private UcenterMemberService ucenterMemberService;

    //1 登录
    @PostMapping("/login")
    public R loginUser(@RequestBody LoginVo loginVo){
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token =ucenterMemberService.login(loginVo);
        return R.ok().data("token",token);
    }

    //2 注册
    @PostMapping("/register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        System.out.println(registerVo);
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    //3 根据token获取用户信息
    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        //调用jwt工具类，根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println("用户id");
        System.out.println(memberId);
        //查询数据库，根据id获取用户信息
        UcenterMember member = ucenterMemberService.getById(memberId);

        return R.ok().data("userInfoMember",member);
    }

    //4 根据id获取用户信息
    @GetMapping("/getUserById/{id}")
    public R getUserById(@PathVariable String id){
        UcenterMember user = ucenterMemberService.getById(id);
        return R.ok().data("user",user);
    }

    //5 修改用户信息
    @PostMapping("/updateUser")
    public R updateUser(@RequestBody UcenterMember ucenterMember){
        ucenterMemberService.updateById(ucenterMember);
        return R.ok();
    }

    //6 查询某一天注册人数
    @GetMapping("/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day){
        Integer count = ucenterMemberService.countRegisterDay(day);
        return R.ok().data("countRegister", count);
    }


}
