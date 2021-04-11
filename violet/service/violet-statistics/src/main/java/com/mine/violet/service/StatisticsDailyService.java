package com.mine.violet.service;

import com.mine.violet.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author violet
 * @since 2020-08-30
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    //统计某一天注册人数,生成统计数据
    void registerCount(String formatDate);

    //图表显示，日期json数据，数量json数据
    Map<String, Object> getShowData(String type, String begin, String end);
}
