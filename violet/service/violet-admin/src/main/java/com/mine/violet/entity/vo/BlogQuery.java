package com.mine.violet.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class BlogQuery {

    @ApiModelProperty(value = "博客标题")
    private  String title;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private  String status;

}
