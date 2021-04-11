package com.mine.violet.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.Blog;
import com.mine.violet.entity.BlogCategory;
import com.mine.violet.entity.BlogTag;
import com.mine.violet.entity.vo.CategoryQuery;
import com.mine.violet.entity.vo.TagQuery;
import com.mine.violet.service.BlogService;
import com.mine.violet.service.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminservice/front/tag")
public class FrontTagController {


    @Autowired
    private BlogTagService blogTagService;
    @Autowired
    private BlogService blogService;

    //1 获取分类list
    @GetMapping("/getTagList")
    public R getTagList(){
        List<BlogTag> tagList = blogTagService.list(null);
        return R.ok().data("tagList",tagList);
    }

    //2 根据分类获取博客
    @PostMapping("/getBlogByTag/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) TagQuery tagQuery){
        Page<Blog> pageBlog = new Page<>(page,limit);
        Map<String,Object> map = blogService.getBlogTagList(pageBlog,tagQuery);
        // 返回分页的所有数据
        return R.ok().data(map);
    }
}
