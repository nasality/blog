package com.bytedance.blog.question.controller;


import com.bytedance.blog.question.service.IQuestionService;
import com.bytedance.blog.api.entities.Question;
import com.bytedance.blog.question.req.QuestionREQ;
import com.bytedance.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 问题信息表 前端控制器
 * </p>
 *
 * @author 似水流年
 * @since 2023-05-05
 */
@Api(value = "问答管理接口")
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;

    @ApiOperation("修改问答信息接口")
    @PutMapping
    public Result update(@RequestBody Question question) {
        return questionService.updateOrSave(question);
    }

    @ApiOperation("新增问答信息接口")
    @PostMapping
    public Result save(@RequestBody Question question) {
        return questionService.updateOrSave(question);
    }

    @ApiOperation("删除问答接口")
    @PutMapping("/{id}")
    public Result updateStatus(@PathVariable("id") String id) {
        return questionService.deleteById(id);
    }

    @ApiOperation("更新点赞数接口")
    @PutMapping("/htumb/{id}/{count}")
    public Result updateThump(@PathVariable("id") String id, @PathVariable("count") int count) {
        return questionService.updateThumhup(id, count);
    }

    @ApiOperation("根据用户id查询问题列表")
    @PostMapping("/user")
    public Result findListByUserId(@RequestBody QuestionREQ req) {
        return questionService.findListByUserId(req);
    }

    @ApiOperation("查询提问总数记录")
    @GetMapping("/total")
    public Result questionTotal() {
        return questionService.getQuestionTotal();
    }
}
