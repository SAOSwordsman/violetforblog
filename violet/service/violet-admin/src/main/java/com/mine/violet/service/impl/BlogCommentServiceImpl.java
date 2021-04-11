package com.mine.violet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mine.violet.entity.BlogComment;
import com.mine.violet.entity.comment.Comment;
import com.mine.violet.entity.comment.Comments;
import com.mine.violet.mapper.BlogCommentMapper;
import com.mine.violet.service.BlogCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author violet
 * @since 2020-08-19
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {


    //前台获取评论集合
    @Override
    public List<Comments> getCommentsList(String blogId) {
        QueryWrapper<BlogComment> wrapper = new QueryWrapper<>();
        wrapper.eq("blogId",blogId);

        List<BlogComment> blogCommentList = baseMapper.selectList(wrapper);

        //赋值给封装类
        List<Comments> commentsList = new ArrayList<>();

        //封装
        for (int i = 0; i < blogCommentList.size(); i++) {
            BlogComment blogComment = blogCommentList.get(i);
            //查找不是回复的评论

            if(blogComment.getParentId().equals("0")){

                Comments comments = new Comments();
                //传递值
                comments.setId(blogComment.getUserId());
                comments.setName(blogComment.getUserName());
                comments.setHeadImg(blogComment.getUserAvatar());
                comments.setComment(blogComment.getContent());
                comments.setTime(blogComment.getGmtCreate());

                //子评论集合
                List<Comment> commentList = new ArrayList<>();
                int flag = 0;
                //查找所有子评论
                for (int j = 0; j < blogCommentList.size(); j++) {
                    BlogComment blogComment1 = blogCommentList.get(j);

                    //判断是不是子评论
                    if(blogComment1.getParentId().equals(blogComment.getId())){
                        flag = 1;
                        Comment comment = new Comment();
                        //传递值
                        comment.setFromId(blogComment1.getUserId());
                        comment.setFrom(blogComment1.getUserName());
                        comment.setFromHeadImg(blogComment1.getUserAvatar());
                        comment.setTo(blogComment.getUserName());
                        comment.setToId(blogComment.getUserId());
                        comment.setComment(blogComment1.getContent());
                        comment.setTime(blogComment1.getGmtCreate());

                        //添加到list集合
                        commentList.add(comment);
                    }
                }
                if(flag==1){
                    comments.setReply(commentList);
                }
                commentsList.add(comments);

            }

        }


        return commentsList;
    }
}
