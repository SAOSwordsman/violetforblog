package com.mine.violet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.UcenterMember;
import com.mine.violet.entity.vo.MemberQuery;
import com.mine.violet.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author violet
 * @since 2020-08-24
 */
@RestController
@RequestMapping("/violetcenter/member")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    //分页查询用户
    @PostMapping("/pageUser/{current}/{limit}")
    public R pageListBlog(@PathVariable long current, @PathVariable long limit,
                          @RequestBody(required = false) MemberQuery memberQuery){
        // 创建page对象
        Page<UcenterMember> userPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();

        String mobile = memberQuery.getMobile();
        String nickname = memberQuery.getNickname();
        if(!StringUtils.isEmpty(mobile)){
            // 构建条件
            wrapper.like("mobile",mobile);
        }
        if(!StringUtils.isEmpty(nickname)){
            // 构建条件
            wrapper.like("nickname",nickname);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        ucenterMemberService.page(userPage,wrapper);

        long total = userPage.getTotal(); //总记录数
        List<UcenterMember> records = userPage.getRecords();

        Map map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    //2 逻辑删除用户
    @DeleteMapping("/deleteById/{id}")
    public R deleteById(@PathVariable String id){
        ucenterMemberService.deleteById(id);
        return R.ok();
    }

    //3 删除用户
    @DeleteMapping("/removeById/{id}")
    public R removeById(@PathVariable String id){
        ucenterMemberService.removeById(id);
        return R.ok();
    }


}

