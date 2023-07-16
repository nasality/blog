package com.bytedance.blog.question.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bytedance.blog.api.entities.Question;
import com.bytedance.blog.api.entities.UserInfoREQ;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 问题信息表 Mapper 接口
 * </p>
 *
 * @author 似水流年
 * @since 2023-05-05
 */
public interface QuestionMapper extends BaseMapper<Question> {
    IPage<Question> findListByLabel(IPage<Question> page, @Param("labelId") String labelId);

    Question findQuestionAndLabelIdsById(@Param("id") String id);

    boolean deleteQuestionLabel(@Param("questionId") String questionId);

    boolean saveQuestionLabel(@Param("questionId") String questionId, @Param("labelIds")List<String> labelIds);

    boolean updateUserInfo(UserInfoREQ req);
}
