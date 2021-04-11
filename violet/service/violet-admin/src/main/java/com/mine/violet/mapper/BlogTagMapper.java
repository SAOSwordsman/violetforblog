package com.mine.violet.mapper;

import com.mine.violet.entity.BlogTag;
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
public interface BlogTagMapper extends BaseMapper<BlogTag> {

    //2 逻辑删除标签
    void updateId(@Param("tagId") String tagId,@Param("deleteId") int deleteId);
}
