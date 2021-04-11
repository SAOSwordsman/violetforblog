package com.mine.violet.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value="登录对象", description="登录对象")
public class LoginVo {

    @ApiModelProperty(value = "账号")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;

}