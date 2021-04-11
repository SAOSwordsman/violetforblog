package com.mine.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.entity.Blog;
import com.mine.violet.entity.vo.CategoryQuery;
import com.mine.violet.entity.vo.TagQuery;
import com.mine.violet.mapper.BlogMapper;
import com.mine.violet.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author violet
 * @since 2020-08-11
 */
@Transactional
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Override
    public Map<String, Object> getBlogCategoryList(Page<Blog> pageBlog, CategoryQuery categoryQuery) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(categoryQuery.getCategory())){
            wrapper.eq("category_name",categoryQuery.getCategory());
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        baseMapper.selectPage(pageBlog, wrapper);
        List<Blog> records = pageBlog.getRecords();
        long current = pageBlog.getCurrent();
        long pages = pageBlog.getPages();
        long size = pageBlog.getSize();
        long total = pageBlog.getTotal();
        boolean hasNext = pageBlog.hasNext(); //是否有上一页
        boolean hasPrevious = pageBlog.hasPrevious(); //是否有下一页

        //把分页数据取出来，放到map集合中
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public Map<String, Object> getBlogTagList(Page<Blog> pageBlog, TagQuery tagQuery) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(tagQuery.getTag())){
            wrapper.eq("blog_tags",tagQuery.getTag());
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        baseMapper.selectPage(pageBlog, wrapper);
        List<Blog> records = pageBlog.getRecords();
        long current = pageBlog.getCurrent();
        long pages = pageBlog.getPages();
        long size = pageBlog.getSize();
        long total = pageBlog.getTotal();
        boolean hasNext = pageBlog.hasNext(); //是否有上一页
        boolean hasPrevious = pageBlog.hasPrevious(); //是否有下一页

        //把分页数据取出来，放到map集合中
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }
}
