package com.bytedance.blog.article.api;

import com.bytedance.blog.article.service.ICommentService;
import com.bytedance.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "评论公开接口管理", description = "评论接口管理，提供评论增删改查")
@RestController
@RequestMapping("/comment/api")
public class ApiCommentController {
    @Autowired
    private ICommentService commentService;

    @ApiOperation("根据文章id查询文章下的评论及子评论")
    @GetMapping("/list/{articleId}")
    public Result findByArticleId(@PathVariable("articleId") String articleId) {
        return commentService.findByArticleId(articleId);
    }
}
