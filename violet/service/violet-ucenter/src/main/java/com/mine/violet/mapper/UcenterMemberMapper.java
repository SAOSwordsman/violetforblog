package com.mine.violet.mapper;

import com.mine.violet.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author violet
 * @since 2020-08-25
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    //逻辑删除
    void updateId(@Param("id") String id,@Param("deleteId") int deleteId);

    //查询某一天注册人数
    Integer countRegisterDay(String day);
}
