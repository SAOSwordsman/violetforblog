package com.mine.violet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mine.violet.entity.vo.CategoryQuery;
import com.mine.violet.entity.vo.TagQuery;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author violet
 * @since 2020-08-11
 */
public interface BlogService extends IService<Blog> {

    //1 根据分类获取博客
    Map<String, Object> getBlogCategoryList(Page<Blog> pageBlog, CategoryQuery categoryQuery);

    //2 根据标签获取博客
    Map<String, Object> getBlogTagList(Page<Blog> pageBlog, TagQuery tagQuery);
}
