package com.bytedance.blog.article.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bytedance.blog.article.service.ICommentService;
import com.bytedance.blog.api.entities.Comment;
import com.bytedance.blog.article.mapper.CommentMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.blog.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 评论信息表 服务实现类
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-22
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public Result findByArticleId(String articleId) {
        if (StringUtils.isEmpty(articleId)) {
            return Result.error("文章id不能为空");
        }
        List<Comment> list = baseMapper.findByArticleId(articleId);
        return Result.ok(list);
    }

    @Override
    public Result deleteById(String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error("id不能为空");
        }
        //要删除的所有评论的Id
        List<String> ids = new ArrayList<>();
        //将当前评论放入集合中
        ids.add(id);
        //递归所有评论id，并将id放入删除集合中
        this.getIds(ids, id);
        //批量删除集合中的评论
        baseMapper.deleteBatchIds(ids);
        return Result.ok();
    }

    private void getIds(List<String> ids, String parentId) {
        //查询子评论
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<Comment> commentList = baseMapper.selectList(wrapper);
        //如果子评论不为空，则取出每条评论的评论id
        if (!CollectionUtils.isEmpty(commentList)) {
            for (Comment comment : commentList) {
                String id = comment.getId();
                ids.add(id);
                this.getIds(ids, id);
            }
        }
    }
}
