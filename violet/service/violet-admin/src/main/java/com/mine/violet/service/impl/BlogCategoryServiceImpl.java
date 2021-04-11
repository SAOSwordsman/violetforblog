package com.mine.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mine.violet.entity.BlogCategory;
import com.mine.violet.mapper.BlogCategoryMapper;
import com.mine.violet.service.BlogCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Override
    public void deleteById(String categoryId) {
        BlogCategory blogCategory = baseMapper.selectById(categoryId);
        int deleteId=0;
        if(blogCategory.getIsDelete()==0){
            deleteId=1;
            baseMapper.updateId(categoryId,deleteId);
        }else{
            deleteId=0;
            baseMapper.updateId(categoryId,deleteId);
        }
    }


}
