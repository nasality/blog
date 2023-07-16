package com.bytedance.blog.api.feign;

import com.bytedance.blog.api.entities.UserInfoREQ;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "question-server", path = "/question")
public interface IFeignQuestionController {

    @ApiOperation("feign接口，修改用户昵称，头像")
    @PutMapping("/feign/question/user")
    boolean updateUserInfo(UserInfoREQ req);
}
