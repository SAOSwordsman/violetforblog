package com.mine.violet.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SayQuery {

    private String title;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date gmtCreate;
}
