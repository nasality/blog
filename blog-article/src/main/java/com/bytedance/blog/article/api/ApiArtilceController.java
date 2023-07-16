package com.bytedance.blog.article.api;

import com.bytedance.blog.article.service.IArticleService;
import com.bytedance.blog.article.req.ArticleListREQ;
import com.bytedance.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "文章公开接口管理", description = "文章接口管理，提供文章增删改查")
@RestController
@RequestMapping("/article/api")
public class ApiArtilceController {
    @Autowired
    private IArticleService articleService;

    @ApiOperation("文章查询详情接口")
    @GetMapping("/{id}")
    public Result view(@PathVariable String id) {
        return articleService.findArticleAndLabelById(id);
    }

    @ApiOperation("更新浏览次数")
    @PutMapping("/viewCount/{id}")
    public Result updateViewCount(@PathVariable("id") String id) {
        return articleService.updateViewCount(id);
    }

    @ApiOperation("查询某个分类下的文章列表")
    @PostMapping("/list")
    public Result list(@RequestBody ArticleListREQ req) {
        return articleService.findListByLabelIdOrCategoryId(req);
    }

   /* @ApiOperation("统计已审核文章总记录数")
    public Result totalNumOfIsChecked(@PathVariable("id") String id) {
        return articleService.getArticleTotal();
    }

    @ApiOperation("统计各分类下的文章数")
    @GetMapping("")*/
}
