package com.bytedance.blog.article.controller;

import com.bytedance.blog.article.service.ICategoryService;
import com.bytedance.blog.api.entities.Category;
import com.bytedance.blog.article.req.CategoryREQ;
import com.bytedance.blog.util.base.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/category")
@RestController

public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/search")
    //不管前端传什么都转换成json
    public Result search(@RequestBody CategoryREQ req) {
        return categoryService.queryPage(req);
    }

    @ApiOperation("查询商品类型")
    @ApiImplicitParam(name = "id", value = "分类id", required = true)
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id) {
        Category category = categoryService.getById(id);
        return Result.ok(category);
    }

    @ApiOperation("修改分类信息接口")
    @PutMapping   //localhost:8001/article/category
    public Result update(@RequestBody Category category) {
        //这里重写了updateById
        categoryService.updateById(category);
        return Result.ok();
    }

    @ApiOperation("新增分类接口")
    @PostMapping
    public Result save(Category category) {
        //创建时间和修改时间在数据库中使用了默认值：当前时间
        categoryService.save(category);
        return Result.ok();
    }

    @ApiOperation("删除类别接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        categoryService.removeById(id);
        return Result.ok();
    }

    @GetMapping("/list")
    @ApiOperation("获取正常分类")
    public Result list() {
        return categoryService.findAllNormal();
    }

    @ApiOperation("查询正常状态分类下的所有标签")
    @GetMapping("/label/list")
    public Result findCategoryAndLabel() {
        return categoryService.findCategoryAndLabel();
    }

}
