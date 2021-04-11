package com.mine.violet.controller.front;


import com.mine.violet.commonutils.R;
import com.mine.violet.entity.Blog;
import com.mine.violet.entity.BlogCategory;
import com.mine.violet.entity.BlogTag;
import com.mine.violet.service.BlogCategoryService;
import com.mine.violet.service.BlogService;
import com.mine.violet.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminservice/front/writeblog")
public class WriteBlogController {


    @Autowired
    private BlogService blogService; //博客
    @Autowired
    private BlogCategoryService blogCategoryService;
    @Autowired
    private BlogTagService blogTagService;

    //1 查询所有分类
    @GetMapping("/getAllCategory")
    public R getAllCategory(){
        List<BlogCategory> list = blogCategoryService.list(null);
        return R.ok().data("categorys",list);
    }

    //2 查询所有标签
    @GetMapping("/getAllTag")
    public R getAllTag(){
        List<BlogTag> list = blogTagService.list(null);
        return R.ok().data("tags",list);
    }


    //3 添加博客
    @PostMapping("/addBlog")
    public R addBlog(@RequestBody Blog blog){
        blogService.save(blog);
        System.out.println(blog.getContent());
        return R.ok();
    }

    //4 通过id查询博客
    @GetMapping("/getBlogById/{id}")
    public R getBlogById(@PathVariable String id){
        Blog blog = blogService.getById(id);
        return R.ok().data("blog",blog);
    }

    //5 修改博客
    @PostMapping("/updateBlog")
    public R updateBlog(@RequestBody Blog blog){
        blogService.updateById(blog);
        return R.ok();
    }

}
