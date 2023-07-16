package com.bytedance.blog.api.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@ApiModel("更新用户信息远程请求类")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoREQ implements Serializable {

    @ApiModelProperty(value = "用户id", required = true)
    private String userId;

    @ApiModelProperty(value = "用户昵称", required = true)
    private String nickName;

    @ApiModelProperty(value = "用户头像url", required = true)
    private String userImage;
}
