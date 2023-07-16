package com.bytedance.blog.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bytedance.blog.article.req.LabelREQ;
import com.bytedance.blog.api.entities.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author 似水流年
 * @since 2023-04-15
 */
@Mapper
public interface LabelMapper extends BaseMapper<Label> {
    IPage<Label> queryPage(IPage<Label> page, @Param("req") LabelREQ req);
}
