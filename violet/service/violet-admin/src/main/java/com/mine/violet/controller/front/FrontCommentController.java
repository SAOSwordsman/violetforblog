package com.mine.violet.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mine.violet.commonutils.R;
import com.mine.violet.entity.Blog;
import com.mine.violet.entity.BlogComment;
import com.mine.violet.entity.comment.Comment;
import com.mine.violet.entity.comment.CommentVo;
import com.mine.violet.entity.comment.Comments;
import com.mine.violet.service.BlogCommentService;
import com.mine.violet.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/adminservice/front/comment")
public class FrontCommentController {


    @Autowired
    private BlogCommentService blogCommentService;
    @Autowired
    private BlogService blogService;

    //前台获取评论集合
    @GetMapping("/getCommentList/{blogId}")
    public R getCommentList(@PathVariable String blogId){
        List<Comments> comments = blogCommentService.getCommentsList(blogId);
        return R.ok().data("comments",comments);
    }

    //前台添加评论
    @PostMapping("/addComment")
    public R addComment(@RequestBody CommentVo commentVo){
        BlogComment blogComment = new BlogComment();

        Blog blog = blogService.getById(commentVo.getId());

        //封装类
        blogComment.setBlogId(commentVo.getId());
        blogComment.setBlogTitle(blog.getTitle());

        if(!StringUtils.isEmpty(commentVo.getCom())){
            QueryWrapper<BlogComment> wrapper = new QueryWrapper<>();
            wrapper.eq("content",commentVo.getCom());
            BlogComment one = blogCommentService.getOne(wrapper);

            String toId = one.getId();
            blogComment.setParentId(toId);
        }
        blogComment.setUserId(commentVo.getMyId());
        blogComment.setUserName(commentVo.getName());
        blogComment.setUserAvatar(commentVo.getHeadImg());
        blogComment.setContent(commentVo.getComment());

        blogCommentService.save(blogComment);
        return R.ok();
    }

}
