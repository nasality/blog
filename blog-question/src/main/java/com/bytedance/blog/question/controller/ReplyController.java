package com.bytedance.blog.question.controller;


import com.bytedance.blog.question.service.IReplyService;
import com.bytedance.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 回答信息表 前端控制器
 * </p>
 *
 * @author 似水流年
 * @since 2023-05-05
 */
@RestController
@RequestMapping("/replay")
@Api(value = "问答管理接口")
public class ReplyController {
    @Autowired
    private IReplyService replyService;

    @ApiOperation("根据id删除评论")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return replyService.deleteById(id);
    }
}
