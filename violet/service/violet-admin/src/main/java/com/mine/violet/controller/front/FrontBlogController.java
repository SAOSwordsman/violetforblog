package com.mine.violet.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.Blog;
import com.mine.violet.entity.vo.BlogQuery;
import com.mine.violet.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminservice/front/blog")
public class FrontBlogController {

    @Autowired
    private BlogService blogService; //博客

    @PostMapping("/updateViews/{id}")
    public R updateViews(@PathVariable String id){
        Blog blog = blogService.getById(id);
        blog.setViews(blog.getViews()+1);

        blogService.updateById(blog);
        return R.ok();
    }

    //1 前台分页查询博客
    @GetMapping("/pageList/{current}/{limit}")
    public R pageList(@PathVariable long current, @PathVariable long limit){
        // 创建page对象
        Page<Blog> blogPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();

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

    //2 根据id查询博客内容
    @GetMapping("/getBlogById/{id}")
    public R getBlogById(@PathVariable String id){
        Blog blog = blogService.getById(id);
        return R.ok().data("blog",blog);
    }

    //3 前台查询作者自己的博客
    @PostMapping("/pageListById/{current}/{limit}/{id}")
    public R pageListById(@PathVariable long current, @PathVariable long limit,
                          @PathVariable String id,
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
        wrapper.eq("author_id",id);

        //排序
        wrapper.orderByDesc("gmt_create");

        blogService.page(blogPage,wrapper);

        long total = blogPage.getTotal(); //总记录数
        List<Blog> records = blogPage.getRecords();

        Map map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        System.out.println("=========");
        System.out.println(id);
        System.out.println(map);

        return R.ok().data(map);
    }

    //4 根据博客id删除博客
    @DeleteMapping("/{blogId}")
    public R deleteBlog(@PathVariable String blogId){
        blogService.removeById(blogId);
        return R.ok();
    }

}
