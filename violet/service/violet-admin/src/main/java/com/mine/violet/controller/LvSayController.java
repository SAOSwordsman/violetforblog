package com.mine.violet.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.BlogTag;
import com.mine.violet.entity.LvSay;
import com.mine.violet.entity.vo.SayQuery;
import com.mine.violet.entity.vo.TagQuery;
import com.mine.violet.service.LvSayService;
import com.mine.violet.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author violet
 * @since 2020-08-20
 */
@RestController
@RequestMapping("/adminservice/lvSay")
public class LvSayController {

    @Autowired
    private LvSayService lvSayService;

    //1 分页查询公告
    @PostMapping("/pageSay/{current}/{limit}")
    public R pageListTag(@PathVariable long current, @PathVariable long limit,
                         @RequestBody SayQuery sayQuery){

        Page<LvSay> sayPage = new Page<>(current,limit);
        String title = sayQuery.getTitle();
        Date gmtCreate = sayQuery.getGmtCreate();

        QueryWrapper<LvSay> wrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(title)){
            // 构建条件
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(gmtCreate)){
            //日期工具类
            String gmtCreate1 = DateUtil.formatDate(gmtCreate);
            String gmtCreate2 = DateUtil.formatDate(DateUtil.addDays(gmtCreate,+1));
            // 构建条件
            wrapper.between("gmt_create",gmtCreate1,gmtCreate2);
        }
        //排序
        wrapper.orderByAsc("gmt_create");

        lvSayService.page(sayPage,wrapper);

        long total = sayPage.getTotal();
        List<LvSay> records = sayPage.getRecords();

        Map map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }


    //2 删除公告
    @DeleteMapping("/removeById/{id}")
    public R removeById(@PathVariable String id){
        lvSayService.removeById(id);
        return R.ok();
    }

    //4 根据id查询公告
    @GetMapping("/getSayById/{id}")
    public R getTagById(@PathVariable String id){
        LvSay lvSay = lvSayService.getById(id);
        return R.ok().data("lvSay",lvSay);
    }

    //5 修改课程信息
    @PostMapping("/updateSay")
    public R updateSay(@RequestBody LvSay lvSay){
        lvSayService.updateById(lvSay);
        return R.ok();
    }

    //6 添加分类
    @PostMapping("/addSay")
    public R addSay(@RequestBody LvSay lvSay){
        lvSayService.save(lvSay);
        return R.ok();
    }

}

