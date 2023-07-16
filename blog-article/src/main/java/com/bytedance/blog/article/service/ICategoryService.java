package com.bytedance.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.blog.api.entities.Category;
import com.bytedance.blog.article.req.CategoryREQ;
import com.bytedance.blog.util.base.Result;

public interface ICategoryService extends IService<Category> {
    Result queryPage(CategoryREQ req);

    Result findAllNormal();
    Result findCategoryAndLabel();
}
