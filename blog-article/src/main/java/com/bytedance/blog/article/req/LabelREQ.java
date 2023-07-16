package com.bytedance.blog.article.req;

import com.bytedance.blog.api.entities.Label;
import com.bytedance.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "二级菜单Label通用查询对象", description = "二级菜单查询条件")
public class LabelREQ extends BaseRequest<Label> {
    @ApiModelProperty(value = "标签名称")
    private String name;
    @ApiModelProperty(value = "标签所属分类ID")
    private String categoryId;
}
