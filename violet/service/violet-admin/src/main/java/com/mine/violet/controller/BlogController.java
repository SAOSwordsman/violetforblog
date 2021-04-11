package com.mine.violet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.Blog;
import com.mine.violet.entity.vo.BlogQuery;
import com.mine.violet.entity.vo.Sum;
import com.mine.violet.service.*;
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
@RequestMapping("/adminservice/blog")
public class BlogController {

    @Autowired
    private BlogService blogService; //博客
    @Autowired
    private BlogCategoryService blogCategoryService; //分类
    @Autowired
    private BlogTagService blogTagService; //标签
    @Autowired
    private BlogCommentService blogCommentService; //评论
    @Autowired
    private FriendlinkService friendlinkService;


    //1 查询博客，标签等的数量
    @GetMapping("/sum")
    public R getSum(){
        Sum sum = new Sum();
        int blogs = blogService.count(null);
        int categorys = blogCategoryService.count(null);
        int tags = blogTagService.count(null);
        int comments = blogCommentService.count(null);
        int friendLinks = friendlinkService.count(null);
        sum.setBlogs(blogs);
        sum.setCategorys(categorys);
        sum.setTags(tags);
        sum.setComments(comments);
        sum.setFriendLinks(friendLinks);

        return R.ok().data("sum",sum);
    }




    //2 分页查询博客
    @PostMapping("/pageBlog/{current}/{limit}")
    public R pageListBlog(@PathVariable long current, @PathVariable long limit,
                            @RequestBody(required = false) BlogQuery blogQuery){
        // 创建page对象
        Page<Blog> blogPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();

        String title = blogQuery.getTitle();
        String status = blogQuery.getStatus();
        if(!StringUtils.isEmpty(title)){
            // 构建条件
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)){
            // 构建条件
            wrapper.eq("status",status);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        blogService.page(blogPage,wrapper);

        long total = blogPage.getTotal(); //总记录数
        List<Blog> records = blogPage.getRecords();

        Map map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    //3 根据博客id删除博客
    @DeleteMapping("/{blogId}")
    public R deleteBlog(@PathVariable String blogId){
        blogService.removeById(blogId);
        return R.ok();
    }

}

