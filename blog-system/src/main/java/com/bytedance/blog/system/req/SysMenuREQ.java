package com.bytedance.blog.system.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SysMenu通用请求对象", description = "菜单列表查询条件")
public class SysMenuREQ {
    @ApiModelProperty(value = "菜单名称")
    private String name;
}
