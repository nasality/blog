package com.bytedance.blog.question.api;


import com.bytedance.blog.question.service.IReplyService;
import com.bytedance.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/replay/api")
@Api(value = "问答管理公开接口")
public class ApiReplyController {
    @Autowired
    private IReplyService replyService;

    @ApiOperation("根据问答ID查询所有评论")
    @GetMapping("/list/{questionId}")
    public Result findByQuestionId(@PathVariable String questionId) {
        return replyService.findByQuestionId(questionId);
    }
}
