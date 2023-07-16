package com.bytedance.blog.system.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("检查密码是否正确")
public class SysUserCheckPasswordREQ {
    @ApiModelProperty("旧密码")
    private String oldPassword;

    @ApiModelProperty("用户id")
    private String id;
}
