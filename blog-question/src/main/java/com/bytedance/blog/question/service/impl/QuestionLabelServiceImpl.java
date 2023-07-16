package com.bytedance.blog.question.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.blog.question.mapper.QuestionLabelMapper;
import com.bytedance.blog.question.service.IQuestionLabelService;
import com.bytedance.blog.api.entities.QuestionLabel;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问题标签中间表 服务实现类
 * </p>
 *
 * @author 似水流年
 * @since 2023-05-05
 */
@Service
public class QuestionLabelServiceImpl extends ServiceImpl<QuestionLabelMapper, QuestionLabel> implements IQuestionLabelService {

}
