package com.bytedance.blog.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bytedance.blog.article.mapper.LabelMapper;
import com.bytedance.blog.article.req.LabelREQ;
import com.bytedance.blog.article.service.ILabelService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.api.entities.Label;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-15
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {
    @Override
    public Result queryPage(LabelREQ req) {
        IPage<Label> page = baseMapper.queryPage(req.getPage(), req);
        return Result.ok(page);
    }


}
