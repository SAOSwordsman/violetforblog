package com.mine.violet.mapper;

import com.mine.violet.entity.BlogCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author violet
 * @since 2020-08-11
 */
public interface BlogCategoryMapper extends BaseMapper<BlogCategory> {

    // 逻辑恢复
    void updateId(@Param("categoryId") String categoryId,@Param("deleteId") int deleteId);


}
