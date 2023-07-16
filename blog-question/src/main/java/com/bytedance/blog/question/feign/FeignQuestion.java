package com.bytedance.blog.question.feign;


import com.bytedance.blog.question.service.IQuestionService;
import com.bytedance.blog.api.feign.IFeignQuestionController;
import com.bytedance.blog.api.entities.UserInfoREQ;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "返回远程调用的问题微服务接口", description = "远程调用问题微服务")
@RestController
public class FeignQuestion implements IFeignQuestionController {
    @Autowired
    private IQuestionService questionService;
    @Override
    public boolean updateUserInfo(UserInfoREQ req) {
        return questionService.updateUserInfo(req);
    }
}
