package com.mine.violet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.BlogCategory;
import com.mine.violet.entity.BlogTag;
import com.mine.violet.entity.vo.TagQuery;
import com.mine.violet.service.BlogTagService;
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
@RequestMapping("/adminservice/tag")
public class BlogTagController {

    @Autowired
    private BlogTagService blogTagService;

    //分页查询标签
    @PostMapping("/pageTag/{current}/{limit}")
    public R pageListTag(@PathVariable long current, @PathVariable long limit,
                         @RequestBody TagQuery tagQuery){
        Page<BlogTag> tagPage = new Page<>(current,limit);
        String tag = tagQuery.getTag();

        QueryWrapper<BlogTag> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(tag)){
            // 构建条件
            wrapper.like("tag",tag);
        }
        //排序
        wrapper.orderByAsc("gmt_create");

        blogTagService.page(tagPage,wrapper);

        long total = tagPage.getTotal();
        List<BlogTag> records = tagPage.getRecords();

        Map map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    //2 逻辑删除标签
    @DeleteMapping("/deleteById/{tagId}")
    public R deleteById(@PathVariable String tagId){
        blogTagService.deleteById(tagId);
        return R.ok();
    }

    //3 删除标签
    @DeleteMapping("/removeById/{tagId}")
    public R removeById(@PathVariable String tagId){
        blogTagService.removeById(tagId);
        return R.ok();
    }

    //4 根据id查询课程
    @GetMapping("/getTagById/{tagId}")
    public R getTagById(@PathVariable String tagId){
        BlogTag blogTag = blogTagService.getById(tagId);
        return R.ok().data("blogTag",blogTag);
    }

    //5 修改课程信息
    @PostMapping("/updateTag")
    public R updateTag(@RequestBody BlogTag blogTag){
        blogTagService.updateById(blogTag);
        return R.ok();
    }

    //6 添加分类
    @PostMapping("/addTag")
    public R addTag(@RequestBody BlogTag blogTag){
        blogTagService.save(blogTag);
        return R.ok();
    }

}

