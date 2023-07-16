package com.bytedance.blog.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bytedance.blog.api.entities.Reply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 回答信息表 Mapper 接口
 * </p>
 *
 * @author 似水流年
 * @since 2023-05-05
 */
public interface ReplyMapper extends BaseMapper<Reply> {
    List<Reply> findByQuestionId(@Param("questionId") String questionId);
}
