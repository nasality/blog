package com.bytedance.blog.article.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.blog.api.entities.Comment;
import com.bytedance.blog.util.base.Result;

/**
 * <p>
 * 评论信息表 服务类
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-22
 */
public interface ICommentService extends IService<Comment> {
    Result findByArticleId(String articleId);

    /**
     * 删除评论
     * @param id 评论id
     * @return re
     */
    Result deleteById(String id);
}
