package com.mine.violet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.BlogComment;
import com.mine.violet.entity.vo.CommentQuery;
import com.mine.violet.service.BlogCommentService;
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
 * @since 2020-08-11
 */
@RestController
@RequestMapping("/adminservice/comment")
public class BlogCommentController {


    @Autowired
    private BlogCommentService blogCommentService;

    //1 分页查询评论
    @PostMapping("/pageComment/{current}/{limit}")
    public R pageListBlog(@PathVariable long current, @PathVariable long limit,
                          @RequestBody(required = false) CommentQuery commentQuery){
        Page<BlogComment> commentPage = new Page<>(current,limit);
        QueryWrapper<BlogComment> wrapper = new QueryWrapper<>();

        String blogTitle = commentQuery.getBlogTitle();
        String userName = commentQuery.getUserName();
        if(!StringUtils.isEmpty(blogTitle)){
            // 构建条件
            wrapper.like("blogTitle",blogTitle);
        }
        if(!StringUtils.isEmpty(userName)){
            // 构建条件
            wrapper.like("user_name",userName);
        }

        //排序
        wrapper.orderByDesc("gmt_create");
        blogCommentService.page(commentPage,wrapper);

        long total = commentPage.getTotal();
        List<BlogComment> records = commentPage.getRecords();

        Map map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    //2 根据评论id删除评论
    @DeleteMapping("/{id}")
    public R deleteBlog(@PathVariable String id){
        blogCommentService.removeById(id);
        return R.ok();
    }

}

