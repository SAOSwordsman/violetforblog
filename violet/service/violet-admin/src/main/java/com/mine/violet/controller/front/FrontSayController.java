package com.mine.violet.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.LvSay;
import com.mine.violet.service.LvSayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adminservice/front/say")
public class FrontSayController {

    @Autowired
    private LvSayService lvSayService;

    @GetMapping("/getFrontSay")
    public R getFrontSay(){
        QueryWrapper<LvSay> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");

        List<LvSay> list = lvSayService.list(wrapper);
        return R.ok().data("list",list);
    }

    @GetMapping("/getFrontSayById/{id}")
    public R getFrontSayById(@PathVariable String id){
        LvSay say = lvSayService.getById(id);
        return R.ok().data("say",say);
    }

}
