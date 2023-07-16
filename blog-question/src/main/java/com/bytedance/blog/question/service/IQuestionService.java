package com.bytedance.blog.question.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.blog.api.entities.Question;
import com.bytedance.blog.api.entities.UserInfoREQ;
import com.bytedance.blog.question.req.QuestionREQ;
import com.bytedance.blog.util.base.BaseRequest;
import com.bytedance.blog.util.base.Result;

/**
 * <p>
 * 问题信息表 服务类
 * </p>
 *
 * @author 似水流年
 * @since 2023-05-05
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 按热度排序问答列表
     * @param req
     * @return
     */
    Result findHotList(BaseRequest<Question> req);

    /**
     * 最新问答分页列表
     * @param req
     * @return
     */
    Result findNewList(BaseRequest<Question> req);

    /**
     * 未回答列表
     * @param req
     * @return
     */
    Result findWaitList(BaseRequest<Question> req);

    /**
     * 根据labelId查询问题
     * @param req
     * @param labelId
     * @return
     */
    Result findListByLabelId(BaseRequest<Question> req, String labelId);

    /**
     * 问答详情页
     * 查询问答所属的标签ids
     * 通过openFeign远程调用Article微服务查询
     * 查询问题对应的回答列表
     */


    /**
     * 查询问答详情
     * @param id
     * @return
     */
    Result findById(String id);

    /**
     * 更新浏览次数，如果用户点击了文章，浏览次数加一
     * @param id
     * @return
     */
    Result updateViewCount(String id);

    /**
     * 问题新增与更新
     * @param question
     * @return
     */
    Result updateOrSave(Question question);

    /**
     * 修改问答状态
     * @param id
     * @param status
     * @return
     */
    Result updateStatus(String id, Integer status);

    /**
     * 删除问答
     * @param id
     * @return
     */
    Result deleteById(String id);

    /**
     * 更新点赞数
     * @param id
     * @param count
     * @return
     */
    Result updateThumhup(String id, int count);

    /**
     * 根据用户id查询问题列表
     * @param req
     * @return
     */
    Result findListByUserId(QuestionREQ req);

    /**
     * 获取问答总数
     * @return
     */
    Result getQuestionTotal();

    Result findByQuestionId();

    /**
     * 修改用户昵称头像
     * @param req
     * @return
     */
    boolean updateUserInfo(UserInfoREQ req);
}
