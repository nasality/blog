package com.bytedance.blog.article.req;

import com.bytedance.blog.util.base.BaseRequest;
import com.bytedance.blog.api.entities.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "查询用户访问通用对象")
public class ArticleUserREQ extends BaseRequest<Article> {
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "是否公开")
    private String isPublic;
}
