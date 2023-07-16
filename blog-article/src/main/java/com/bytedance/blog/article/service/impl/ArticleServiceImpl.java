package com.bytedance.blog.article.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.blog.article.service.IArticleService;
import com.bytedance.blog.api.entities.Article;
import com.bytedance.blog.article.mapper.ArticleMapper;
import com.bytedance.blog.article.req.ArticleListREQ;
import com.bytedance.blog.article.req.ArticleREQ;
import com.bytedance.blog.article.req.ArticleUserREQ;
import com.bytedance.blog.api.entities.UserInfoREQ;
import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.util.enums.ArticleStatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;


import java.util.*;

/**
 * <p>
 * 文章信息表 服务实现类
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-15
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public Result queryPage(ArticleREQ req) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(req.getTitle())) {
            wrapper.like("title", req.getTitle());
        }
        if (req.getStatus() != null) {
            wrapper.eq("status", req.getStatus());
        }
        wrapper.orderByDesc("update_date");
        IPage<Article> page = baseMapper.selectPage(req.getPage(), wrapper);
        return Result.ok(page);

    }

    @Override
    public Result findArticleAndLabelById(String id) {
        return Result.ok(baseMapper.findArticleAndLabelById(id));
    }

    @Override
    public Result updateOrSave(Article article) {
        //如果id不为空，则为update操作
        if (StringUtils.isNotEmpty(article.getId())) {
            //更新，先删除中间表数据
            baseMapper.deleteArticleLabel(article.getId());
            //将更新时间设置为当前时间
            article.setUpdateDate(new Date());
        }else {
            article.setStatus(ArticleStatusEnum.NOTCHECK.getCode());
        }
        //如果文章是非公开，则直接审核通过
        if (article.getIspublic() == 0) {
            article.setStatus(ArticleStatusEnum.SUCCESS.getCode());
        }
        super.saveOrUpdate(article);
        if (CollectionUtils.isNotEmpty(article.getLabelIds())) {
            baseMapper.saveArticleLabel(article.getId(), article.getLabelIds());
        }
        return Result.ok(article.getId());
    }

    @Override
    public Result updateStatus(String id, int code) {
        Article article = baseMapper.selectById(id);
        article.setStatus(code);
        article.setUpdateDate(new Date());
        baseMapper.updateById(article);
        return Result.ok();
    }

    @Override
    public Result findListByUserId(ArticleUserREQ req) {
        if (StringUtils.isEmpty(req.getUserId())) {
            return Result.error("无效用户信息");
        }
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", req.getUserId());
        if (req.getIsPublic() != null) {
            wrapper.eq("ispublic", req.getIsPublic());
        }
        wrapper.orderByDesc("update_date");
        IPage<Article> page = baseMapper.selectPage(req.getPage(), wrapper);
        return Result.ok(page);
    }

    @Override
    public Result updateThumhup(String id, int count) {
        if (count != -1 && count != 1 || StringUtils.isEmpty(id)) {
            return Result.error("无效操作");
        }
        Article article = baseMapper.selectById(id);
        if (article == null) {
            return Result.error("文章不存在");
        }
        if (article.getThumhup() <= 0 && count == -1) {
            return Result.error("无效操作");
        }
        article.setThumhup(article.getThumhup() + count);
        baseMapper.updateById(article);
        return Result.ok();
    }

    @Override
    public Result updateViewCount(String id) {
        if (StringUtils.isEmpty(id)) {
            return Result.error("无效操作");
        }
        Article article = baseMapper.selectById(id);
        if (article == null) {
            return Result.error("文章不存在");
        }
        article.setViewCount(article.getViewCount() + 1);
        baseMapper.updateById(article);
        return Result.ok();
    }



    @Override
    public Result findListByLabelIdOrCategoryId(ArticleListREQ req) {
        IPage<Article> page = baseMapper.findListByLabelIdOrCategoryId(req.getPage(),req);
        return Result.ok(page);
    }

    @Override
    public Result selectCategoryTotal() {
        List<Map<String, Object>> maps = baseMapper.selectCategoryTotal();
        List<Object> nameList = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            nameList.add(map.get("name"));
        }
        Map<String, Object> data = new HashMap<>();
        data.put("nameAndValue", maps);
        data.put("nameList", nameList);
        return Result.ok(data);
    }

    @Override
    public Result getArticleTotal() {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.eq("status", ArticleStatusEnum.SUCCESS.getCode());
        wrapper.eq("ispublic", 1);
        //MyBatis自有方法，count
        Integer total = baseMapper.selectCount(wrapper);
        return Result.ok(total);
    }

    @Override
    public boolean updateUserInfo(UserInfoREQ req) {
        return baseMapper.updateUserInfo(req);
    }
}
