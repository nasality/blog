package com.bytedance.blog.article.req;

import com.bytedance.blog.util.base.BaseRequest;
import com.bytedance.blog.api.entities.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//此类继承了BaseRequest
@Data
@ApiModel(value = "categoryREQ条件对象", description = "分类查询条件")
public class CategoryREQ extends BaseRequest<Category> {
    //分类名称
    @ApiModelProperty(value = "分类名称")
    private String name;
    //分类状态（1：正常， 0：禁用）
    @ApiModelProperty(value = "分类状态（1：正常，0：禁用）")
    private Integer status;
}
