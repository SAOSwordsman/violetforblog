package com.mine.violet.service;

import com.mine.violet.entity.BlogCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author violet
 * @since 2020-08-11
 */
public interface BlogCategoryService extends IService<BlogCategory> {

    //2 根据id逻辑删除分类
    void deleteById(String categoryId);


}
