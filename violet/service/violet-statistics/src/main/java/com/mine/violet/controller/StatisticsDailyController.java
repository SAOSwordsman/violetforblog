package com.mine.violet.controller;


import com.mine.violet.commonutils.R;
import com.mine.violet.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author violet
 * @since 2020-08-30
 */
@RestController
@RequestMapping("/staservice/sta")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService staService;

    //统计某一天注册人数,生成统计数据
    @PostMapping("/registerCount/{day}")
    public R registerCount(@PathVariable String day){
        staService.registerCount(day);
        return R.ok();
    }

    //图表显示，日期json数据，数量json数据
    @GetMapping("/showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,
                      @PathVariable String begin,@PathVariable String end) {
        Map<String ,Object> map = staService.getShowData(type,begin,end);
        return R.ok().data(map);
    }

}

