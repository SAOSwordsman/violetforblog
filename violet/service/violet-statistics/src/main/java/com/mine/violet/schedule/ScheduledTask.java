package com.mine.violet.schedule;



import com.mine.violet.service.StatisticsDailyService;
import com.mine.violet.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//定时器
@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService staService;

    //在每天凌晨1点，把前一天的数据进行添加，执行方法，把数据查询进行添加
    @Scheduled(cron = "0 0 1 * * ?")
    public void task1(){
        staService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));
    }

}
