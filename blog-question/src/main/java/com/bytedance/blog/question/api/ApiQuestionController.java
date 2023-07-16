package com.bytedance.blog.question.api;

import com.bytedance.blog.question.service.IQuestionService;
import com.bytedance.blog.api.entities.Question;
import com.bytedance.blog.util.base.BaseRequest;
import com.bytedance.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "问答管理公开接口")
@RequestMapping("/question/pi")
@RestController
public class ApiQuestionController {
    @Autowired
    private IQuestionService questionService;

    @ApiOperation("分页查询热门问答接口")
    @PostMapping("newhot")
    public Result findHotList(@RequestBody BaseRequest<Question> req) {
        return questionService.findHotList(req);
    }

    @ApiOperation("分页查询最新问答接口")
    @PostMapping("/new")
    public Result findNewList(@RequestBody BaseRequest<Question> req) {
        return questionService.findNewList(req);
    }

    @ApiOperation("分页查询未问答接口")
    @PostMapping("/wait")
    public Result findWaitList(@RequestBody BaseRequest<Question> req) {
        return questionService.findWaitList(req);
    }

    @ApiOperation("分页查询标签下的问答列表接口")
    @PostMapping("/list/{labelId}")
    public Result findListByLabelId(@RequestBody BaseRequest<Question> req, @PathVariable("labelId") String labelId) {
        return questionService.findListByLabelId(req, labelId);
    }

    @ApiOperation("查询问答详情接口")
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id) {
        return questionService.findById(id);
    }

    @ApiOperation("更新浏览次数")
    @PutMapping("/viewcount/{id}")
    public Result updateViewCount(String id) {
        return questionService.updateViewCount(id);
    }
}
