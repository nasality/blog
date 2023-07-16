package com.bytedance.blog.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bytedance.blog.article.req.ArticleListREQ;
import com.bytedance.blog.api.entities.Article;
import com.bytedance.blog.api.entities.UserInfoREQ;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章信息表 Mapper 接口
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-15
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    Article findArticleAndLabelById(String id);

    /**
     * 新增文章标签中间表数据
     */
    boolean saveArticleLabel(@Param("articleId") String articleId, @Param("labelIds") List<String> listIds);

    /**
     * 通过文章id删除中间表数据
     */
    boolean deleteArticleLabel(@Param("articleId") String articleId);

    /**
     * 查询分类或标签下的文章
     * @param page 分页
     * @param req 请求包装类
     * @return 查询结果
     */
    IPage<Article> findListByLabelIdOrCategoryId(IPage<Article> page, @Param("req") ArticleListREQ req);

    /**
     *查询每个分类下的文章数量
     */
    List<Map<String, Object>> selectCategoryTotal();

    /**
     * 更新用户昵称和头像
     * @param req
     * @return
     */
    boolean updateUserInfo(UserInfoREQ req);
}
