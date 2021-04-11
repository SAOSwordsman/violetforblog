package com.mine.violet.entity.comment;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private String fromId;
    private String from;
    private String fromHeadImg;
    private String to;
    private String toId;
    private String comment;
    private Date time;

    private Boolean inputShow = false;


}
