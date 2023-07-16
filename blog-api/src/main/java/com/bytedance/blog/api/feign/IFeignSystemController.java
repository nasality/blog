package com.bytedance.blog.api.feign;

import com.bytedance.blog.api.entities.SysMenu;
import com.bytedance.blog.api.entities.SysUser;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "System-server", path = "/system")
public interface IFeignSystemController {

    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    @ApiOperation("Feign接口-通过用户名获取用户信息")
    @GetMapping("/api/feign/user/{username}")
    SysUser findUserByUsername(@PathVariable("username") String username);

    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    @ApiOperation("Feign接口-通过用户id获取用户权限")
    @GetMapping("/api/feign/user/{userId}")
    List<SysMenu> findMenuByUserId(@PathVariable("userId") String userId);
}
