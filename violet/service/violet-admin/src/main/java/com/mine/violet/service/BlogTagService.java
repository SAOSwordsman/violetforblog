package com.mine.violet.service;

import com.mine.violet.entity.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author violet
 * @since 2020-08-11
 */
public interface BlogTagService extends IService<BlogTag> {

    //2 逻辑删除标签
    void deleteById(String tagId);


}
