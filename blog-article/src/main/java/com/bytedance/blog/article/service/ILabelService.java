package com.bytedance.blog.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.blog.article.req.LabelREQ;
import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.api.entities.Label;

/**
 * <p>
 * 标签表 服务类
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-15
 */
public interface ILabelService extends IService<Label> {
    Result queryPage(LabelREQ req);
}
