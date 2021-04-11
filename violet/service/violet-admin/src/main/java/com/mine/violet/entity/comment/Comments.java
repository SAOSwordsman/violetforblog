package com.mine.violet.entity.comment;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Comments {

    private String id;
    private String name;
    private String headImg;
    private String comment;
    private Date time;
    private Boolean inputShow = false;

    private List<Comment> reply = new ArrayList<>();


}
