package com.bytedance.blog.article.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bytedance.blog.api.entities.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论信息表 Mapper 接口
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-22
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> findByArticleId(@Param("articleId") String articleId);
}
