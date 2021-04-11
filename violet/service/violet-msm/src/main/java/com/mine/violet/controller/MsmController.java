package com.mine.violet.controller;


import com.mine.violet.commonutils.R;
import com.mine.violet.service.MsmService;
import com.mine.violet.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/violetmsm/msm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    //Redis方法
    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    //发送短信方法
    @GetMapping("/send/{phone}")
    public R sendMsm(@PathVariable String phone){
        //因为阿里云设置验证码在五分钟内有效，所以需要使用redis设置有效时间
        //1 从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return R.ok();
        }

        //2 如果redis获取不到，进行阿里云发送
        //生成随机值，传递给阿里云进行发送,
        //获取四位数字
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);

        //调用servide中发送短信的方法
        boolean isSend = msmService.send(param, phone);
        if(isSend){
            //发送成功把验证码放到redis里面
            //设置有效时间五分钟
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }else {
            return R.error().message("短信发送失败");
        }
    }
}
