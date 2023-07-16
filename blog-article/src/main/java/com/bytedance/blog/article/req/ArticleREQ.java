package com.bytedance.blog.article.req;

import com.bytedance.blog.api.entities.Article;
import com.bytedance.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "文章查询对象",description = "文章查询条件")
@Data
public class ArticleREQ extends BaseRequest<Article> {
    @ApiModelProperty("文章标题")
    private String title;
    @ApiModelProperty("状态0：已删除，1：未审核，2：审核通过，3：审核未通过")
    private Integer status;

}
