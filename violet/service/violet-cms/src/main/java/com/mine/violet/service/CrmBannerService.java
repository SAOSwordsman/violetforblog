package com.mine.violet.service;

import com.mine.violet.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author violet
 * @since 2020-08-23
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllList();

}
