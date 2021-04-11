package com.mine.violet.entity.vo;


import lombok.Data;

//查询博客分类等数量
@Data
public class Sum {

    private int blogs;
    private int tags;
    private int categorys;
    private int comments;
    private int friendLinks;

}
