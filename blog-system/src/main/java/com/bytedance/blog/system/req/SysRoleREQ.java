package com.bytedance.blog.system.req;


import com.bytedance.blog.api.entities.SysRole;
import com.bytedance.blog.util.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "SysRoleREQ通用请求对象", description = "角色查询条件")
public class SysRoleREQ extends BaseRequest<SysRole> {

    @ApiModelProperty(value = "角色名称")
    private String name;
}
