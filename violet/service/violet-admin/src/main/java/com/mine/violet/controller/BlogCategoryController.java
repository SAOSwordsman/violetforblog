package com.mine.violet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.BlogCategory;
import com.mine.violet.entity.vo.BlogQuery;
import com.mine.violet.entity.vo.CategoryQuery;
import com.mine.violet.service.BlogCategoryService;
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
@RequestMapping("/adminservice/category")
public class BlogCategoryController {


    @Autowired
    private BlogCategoryService blogCategoryService;

    //分页查询分类
    @PostMapping("/pageCategory/{current}/{limit}")
    public R pageListBlog(@PathVariable long current, @PathVariable long limit,
                          @RequestBody(required = false) CategoryQuery categoryQuery){
        Page<BlogCategory> categoryPage = new Page<>(current,limit);
        QueryWrapper<BlogCategory> wrapper = new QueryWrapper<>();
        String category = categoryQuery.getCategory();

        if(!StringUtils.isEmpty(category)){
            // 构建条件
            wrapper.like("category",category);
        }
        //排序
        wrapper.orderByAsc("gmt_create");

        blogCategoryService.page(categoryPage,wrapper);

        long total = categoryPage.getTotal();
        List<BlogCategory> records = categoryPage.getRecords();

        Map map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    //2 根据id逻辑删除分类
    @DeleteMapping("/deleteById/{categoryId}")
    public R deleteById(@PathVariable String categoryId){
        blogCategoryService.deleteById(categoryId);
        return R.ok();
    }

    //3 删除分类
    @DeleteMapping("/removeById/{categoryId}")
    public R removeById(@PathVariable String categoryId){
        blogCategoryService.removeById(categoryId);
        return R.ok();
    }

    //4 根据id查询课程
    @GetMapping("/getCategoryById/{categoryId}")
    public R getCategoryById(@PathVariable String categoryId){
        BlogCategory blogCategory = blogCategoryService.getById(categoryId);
        return R.ok().data("blogCategory",blogCategory);
    }

    //5 修改课程信息
    @PostMapping("/updateCategory")
    public R updateCategory(@RequestBody BlogCategory blogCategory){
            blogCategoryService.updateById(blogCategory);
            return R.ok();
    }

    //6 添加分类
    @PostMapping("/addCategory")
    public R addCategory(@RequestBody BlogCategory blogCategory){
        blogCategoryService.save(blogCategory);
        return R.ok();
    }


}

