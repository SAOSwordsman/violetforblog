package com.mine.violet.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.Blog;
import com.mine.violet.entity.Friendlink;
import com.mine.violet.entity.vo.BlogQuery;
import com.mine.violet.service.FriendlinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminservice/front/friend")
public class FrontFriendController {

    @Autowired
    private FriendlinkService friendlinkService;

    //2 分页查询友链
    @PostMapping("/pageFriend/{current}/{limit}")
    public R pageListBlog(@PathVariable long current, @PathVariable long limit){
        // 创建page对象
        Page<Friendlink> friendPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Friendlink> wrapper = new QueryWrapper<>();

        //排序
        wrapper.orderByDesc("gmt_create");

        friendlinkService.page(friendPage,wrapper);

        long total = friendPage.getTotal(); //总记录数
        List<Friendlink> records = friendPage.getRecords();

        Map map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

}
