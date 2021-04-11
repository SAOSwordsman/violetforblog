package com.mine.violet.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.Blog;
import com.mine.violet.entity.BlogCategory;
import com.mine.violet.entity.vo.CategoryQuery;
import com.mine.violet.service.BlogCategoryService;
import com.mine.violet.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/adminservice/front/category")
public class FrontCategoryController {

    @Autowired
    private BlogCategoryService blogCategoryService;
    @Autowired
    private BlogService blogService;

    //1 获取分类list
    @GetMapping("/getCategoryList")
    public R getCategoryList(){
        List<BlogCategory> categoryList = blogCategoryService.list(null);
        return R.ok().data("categoryList",categoryList);
    }

    //2 根据分类获取博客
    @PostMapping("/getBlogByCategory/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CategoryQuery categoryQuery){
        Page<Blog> pageBlog = new Page<>(page,limit);
        Map<String,Object> map = blogService.getBlogCategoryList(pageBlog,categoryQuery);
        // 返回分页的所有数据
        return R.ok().data(map);
    }

}
