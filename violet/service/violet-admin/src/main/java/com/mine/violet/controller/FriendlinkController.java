package com.mine.violet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.BlogTag;
import com.mine.violet.entity.Friendlink;
import com.mine.violet.entity.vo.FriendQuery;
import com.mine.violet.entity.vo.TagQuery;
import com.mine.violet.service.FriendlinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author violet
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/adminservice/friend")
public class FriendlinkController {

    @Autowired
    private FriendlinkService friendlinkService;

    //1 分页查询友链
    @PostMapping("/pageFriend/{current}/{limit}")
    public R pageFriend(@PathVariable long current, @PathVariable long limit,
                        @RequestBody FriendQuery friendQuery){

        Page<Friendlink> friendlinkPage = new Page<>(current,limit);
        String blogger = friendQuery.getBlogger();
        String url = friendQuery.getUrl();

        QueryWrapper<Friendlink> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(blogger)){
            // 构建条件
            wrapper.like("blogger",blogger);
        }
        if(!StringUtils.isEmpty(url)){
            // 构建条件
            wrapper.like("url",url);
        }
        //排序
        wrapper.orderByAsc("gmt_create");

        friendlinkService.page(friendlinkPage,wrapper);

        long total = friendlinkPage.getTotal();
        List<Friendlink> records = friendlinkPage.getRecords();

        Map map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    // 2删除友链
    @DeleteMapping("/removeById/{id}")
    public R removeById(@PathVariable String id){
        friendlinkService.removeById(id);
        return R.ok();
    }

    //根据id查询友链
    @GetMapping("/getFriendById/{id}")
    public R getFriendById(@PathVariable String id){
        Friendlink friendlink = friendlinkService.getById(id);
        return R.ok().data("friendlink",friendlink);
    }

    //5 修改友链信息
    @PostMapping("/updateFriend")
    public R updateFriend(@RequestBody Friendlink friendlink){
        friendlinkService.updateById(friendlink);
        return R.ok();
    }

    //6 添加友链
    @PostMapping("/addFriend")
    public R addFriend(@RequestBody Friendlink friendlink){
        friendlinkService.save(friendlink);
        return R.ok();
    }

}

