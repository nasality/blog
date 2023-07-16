package com.bytedance.blog.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bytedance.blog.api.entities.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> findCategoryAndLabel();
}
