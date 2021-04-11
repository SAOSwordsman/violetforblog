package com.mine.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mine.violet.client.UcenterClient;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.StatisticsDaily;
import com.mine.violet.mapper.StatisticsDailyMapper;
import com.mine.violet.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author violet
 * @since 2020-08-30
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient client;

    //统计某一天注册人数,生成统计数据
    @Override
    public void registerCount(String day) {

        //添加记录之前删除表相同日期的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<StatisticsDaily>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        //远程调用方法
        R registerR = client.countRegister(day);
        int countRegister = (int) registerR.getData().get("countRegister");

        //把获取数据添加到数据库，统计分析表里面
        StatisticsDaily sta = new StatisticsDaily();
        sta.setRegisterNum(countRegister); // 注册人数
        sta.setDateCalculated(day); //统计日期

        sta.setLoginNum(RandomUtils.nextInt(100, 200));

        baseMapper.insert(sta);
    }

    //图表显示，日期json数据，数量json数据
    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);

        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);

        //因为返回有两部分数据，日期和日期对应数量
        //前端要求数组json结构，对应后端
        //创建两个list集合，一个日期，一个数量list
        List<String> date = new ArrayList<>();
        List<Integer> num = new ArrayList<>();

        //遍历查询所有list集合进行封装
        for (int i = 0; i < staList.size(); i++) {
            StatisticsDaily daily = staList.get(i);
            //封装日期
            date.add(daily.getDateCalculated());
            //封装数量
            switch (type) {
                case "register_num":
                    num.add(daily.getRegisterNum());
                    break;
                case "login_num":
                    num.add(daily.getLoginNum());
                    break;
                default:
                    break;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("date_calculated",date);
        map.put("numDataList",num);
        return map;
    }
}
