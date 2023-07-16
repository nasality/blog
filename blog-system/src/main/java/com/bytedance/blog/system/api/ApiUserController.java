package com.bytedance.blog.system.api;

import com.bytedance.blog.system.service.ISysUserService;
import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.system.req.RegisterREQ;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户管理接口", description = "用户管理接口不需要登录")
@RestController
@RequestMapping("/api/user")
public class ApiUserController {
    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation("验证用户名是否被注册")
    @GetMapping("/username/{username}")
    public Result checkUsername(@PathVariable("username") String username) {
        return sysUserService.checkUsername(username);
    }

    @ApiOperation("注册用户")
    @PutMapping("/register")
    public Result register(@RequestBody RegisterREQ req) {
        return sysUserService.register(req);
    }
}