package com.bytedance.blog.system.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("修改密码请求类")
public class SysUserUpdatePasswordREQ extends SysUserCheckPasswordREQ{
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
    @ApiModelProperty(value = "确认密码", required = true)
    private String repPassword;
}
