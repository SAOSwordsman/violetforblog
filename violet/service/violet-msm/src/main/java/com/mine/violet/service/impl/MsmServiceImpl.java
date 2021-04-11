package com.mine.violet.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import com.mine.violet.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {


    //发送短信方法
    @Override
    public boolean send(Map<String, Object> param, String phone) {

        if(StringUtils.isEmpty(phone)) {
            return false;
        }
        //id和密钥是自己阿里云那个统一的
        DefaultProfile profile =
                DefaultProfile.getProfile("default", "LTAI4FrkgcXE2rejy3r22pfE",
                        "V2VRb94pulRGDlIsQn2PjDCib4Jx2a");
        IAcsClient client = new DefaultAcsClient(profile);

        //设置固定的相关参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phone); //手机号
        request.putQueryParameter("SignName", "我的时间海"); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode", "SMS_197891328");//申请阿里云 模板CODE
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));//传递json数据

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
