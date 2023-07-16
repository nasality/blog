package com.bytedance.blog.question.service;

import com.bytedance.blog.api.entities.Reply;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.blog.util.base.Result;

/**
 * <p>
 * 回答信息表 服务类
 * </p>
 *
 * @author 似水流年
 * @since 2023-05-05
 */
public interface IReplyService extends IService<Reply> {

    /**
     * 根据问答ID查询所有评论
     * @param questionId
     * @return
     */
    Result findByQuestionId(String questionId);

    /**
     * 批量删除评论
     * @param id
     * @return
     */
    Result deleteById(String id);
}
