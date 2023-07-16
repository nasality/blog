package com.bytedance.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.blog.article.req.ArticleListREQ;
import com.bytedance.blog.article.req.ArticleREQ;
import com.bytedance.blog.article.req.ArticleUserREQ;
import com.bytedance.blog.api.entities.UserInfoREQ;
import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.api.entities.Article;

/**
 * <p>
 * 文章信息表 服务类
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-15
 */
public interface IArticleService extends IService<Article> {
    Result queryPage(ArticleREQ req);
    Result findArticleAndLabelById(String id);

    Result updateOrSave(Article article);

    Result updateStatus(String id, int code);

    Result findListByUserId(ArticleUserREQ req);

    Result updateThumhup(String id, int count);

    Result updateViewCount(String id);

    Result findListByLabelIdOrCategoryId(ArticleListREQ req);

    Result selectCategoryTotal();

    Result getArticleTotal();

    boolean updateUserInfo(UserInfoREQ req);
}
