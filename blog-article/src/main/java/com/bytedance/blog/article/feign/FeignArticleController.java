package com.bytedance.blog.article.feign;

import com.bytedance.blog.article.service.IArticleService;
import com.bytedance.blog.article.service.ILabelService;
import com.bytedance.blog.api.entities.Label;
import com.bytedance.blog.api.entities.UserInfoREQ;
import com.bytedance.blog.api.feign.IFeignArticleController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "被远程调用的文章微服务接口")
public class FeignArticleController implements IFeignArticleController {
    @Autowired
    private ILabelService labelService;

    private IArticleService articleService;
    @Override
    public List<Label> getLabelListByIds(List<String> labelIds) {
        return labelService.listByIds(labelIds);
    }

    @Override
    public boolean updateUserInfo(UserInfoREQ req) {
        return articleService.updateUserInfo(req);
    }
}
