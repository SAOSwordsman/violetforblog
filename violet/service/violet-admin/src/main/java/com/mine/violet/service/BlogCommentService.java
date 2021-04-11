package com.mine.violet.service;

import com.mine.violet.entity.BlogComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mine.violet.entity.comment.Comments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author violet
 * @since 2020-08-19
 */
public interface BlogCommentService extends IService<BlogComment> {

    //前台获取评论集合
    List<Comments> getCommentsList(@Param("blogId") String blogId);

}
