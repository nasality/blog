package com.bytedance.blog.article.controller;


import com.bytedance.blog.article.service.ICommentService;
import com.bytedance.blog.api.entities.Comment;
import com.bytedance.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论信息表 前端控制器
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-22
 */
@Api(value = "评论管理接口")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @ApiOperation("根据id删除该评论及其所有子评论")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return commentService.deleteById(id);
    }

    @ApiOperation("新增评论接口")
    @PostMapping
    public Result save(@RequestBody Comment comment) {
        commentService.save(comment);
        return Result.ok();
    }
}
