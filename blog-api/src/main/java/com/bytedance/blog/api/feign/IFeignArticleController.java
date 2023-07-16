package com.bytedance.blog.api.feign;

import com.bytedance.blog.api.entities.Label;
import com.bytedance.blog.api.entities.UserInfoREQ;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//value指定目标微服务名字，path上下文路径
@FeignClient(value = "article-server", path = "/article")
public interface IFeignArticleController {
    @ApiImplicitParam(allowMultiple = true,dataType = "String",name = "labelIds", value = "标签ID集合", required = true)
    @ApiOperation("feign接口-根据标签ids查询对应标签信息")
    @PostMapping("/api/feign/label/list/{ids}")
    List<Label> getLabelListByIds(@PathVariable("ids") List<String> labelIds);

    @ApiOperation("修改用户昵称，头像")
    @PutMapping("/feign/article/user")
    boolean updateUserInfo(@RequestBody UserInfoREQ req);
}
