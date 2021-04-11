package com.mine.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mine.violet.entity.CrmBanner;
import com.mine.violet.mapper.CrmBannerMapper;
import com.mine.violet.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author violet
 * @since 2020-08-23
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    //前台查询所有课程
    @Cacheable(key = "'selectIndexList'",value = "banner")
    @Override
    public List<CrmBanner> selectAllList() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 3");

        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
