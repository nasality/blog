package com.bytedance.blog.question.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.blog.question.mapper.ReplyMapper;
import com.bytedance.blog.question.service.IQuestionService;
import com.bytedance.blog.question.service.IReplyService;
import com.bytedance.blog.api.entities.Question;
import com.bytedance.blog.api.entities.Reply;
import com.bytedance.blog.util.base.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 回答信息表 服务实现类
 * </p>
 *
 * @author 似水流年
 * @since 2023-05-05
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

    @Override
    public Result findByQuestionId(String questionId) {
        if (StringUtils.isEmpty(questionId)) {
            return Result.error("问题不能为空");
        }

        return Result.ok(baseMapper.findByQuestionId(questionId));
    }

    @Autowired
    private IQuestionService questionService;

    @Transactional
    @Override
    public Result deleteById(String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error("评论不能为空");
        }
        List<String> ids = new ArrayList<>();
        ids.add(id);
        this.getIds(ids, id);

        //查询回答信息
        Reply reply = baseMapper.selectById(id);
        //批量删除评论信息
        int size = baseMapper.deleteBatchIds(ids);
        //通过查询到的回答信息的问题id去查询问答信息
        if (size > 0) {
            Question question = (Question) questionService.findById(reply.getQuestionId()).getData();
            question.setReply(question.getReply() - size);
            questionService.updateById(question);
        }
        return Result.ok();
    }

    private void getIds(List<String> ids, String parentId) {
        QueryWrapper<Reply> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<Reply> replyList = baseMapper.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(replyList)) {
            for (Reply reply : replyList) {
                String id = reply.getId();
                ids.add(id);
                this.getIds(ids, id);
            }
        }
    }
}
