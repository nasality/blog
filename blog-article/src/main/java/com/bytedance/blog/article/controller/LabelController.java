package com.bytedance.blog.article.controller;

import com.bytedance.blog.article.req.LabelREQ;
import com.bytedance.blog.article.service.ILabelService;
import com.bytedance.blog.util.base.Result;

import com.bytedance.blog.api.entities.Label;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 标签表 前端控制器
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-15
 */
@Api(value = "二级菜单标签管理接口", description = "标签管理接口，提供增删改查功能")
@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private ILabelService labelService;

    @ApiOperation("根据标签名称与分类id查询页面列表")
    @PostMapping("search")
    public Result search(@RequestBody LabelREQ req) {
        return labelService.queryPage(req);
    }

    @ApiOperation("修改标签信息接口")
    @PutMapping
    public Result update(@RequestBody Label label) {
        labelService.updateById(label);
        return Result.ok();
    }

    @ApiOperation("删除标签接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        labelService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("新增标签信息接口")
    @PostMapping
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return Result.ok();
    }
}
