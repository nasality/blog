package com.bytedance.blog.system.controller;


import com.bytedance.blog.api.entities.SysUser;
import com.bytedance.blog.system.req.SysUserREQ;
import com.bytedance.blog.system.req.SysUserUpdatePasswordREQ;
import com.bytedance.blog.system.service.ISysUserService;
import com.bytedance.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
@RestController
@RequestMapping("/sys-user")
@Api(value = "用户管理接口", description = "用户管理接口，提供用户操作功能")
public class SysUserController {
    @Autowired
    private ISysUserService userService;

    @ApiOperation("根据用户名和手机号查询用户列表")
    @PostMapping("/search")
    public Result search(@RequestBody SysUserREQ req) {
        return userService.queryPage(req);
    }

    @ApiOperation("根据用户id查询角色ids")
    @GetMapping("/{id}/role/ids")
    public Result findRoleIdsById(@PathVariable("id") String id) {
        return userService.findRoleIdsById(id);
    }

    @ApiOperation("新增用户角色按钮")
    @PostMapping("/{id}/role/save")
    public Result saveUserRole(@PathVariable("id") String id, @RequestBody List<String> roldIds) {
        return userService.saveUserRole(id, roldIds);
    }

    @ApiOperation("根据用户id删除用户")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return userService.deleteById(id);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ApiOperation("新增用户接口")
    @PostMapping
    public Result save(@RequestBody SysUser sysUser) {
        //密码加密
        String encode = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(encode);
        //新增
        userService.save(sysUser);
        return Result.ok();
    }

    @ApiOperation("根据ID查询用户详情")
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id) {
        return Result.ok(userService.getById(id));
    }

    @ApiOperation("校验原密码")
    @PostMapping("/check/password")
    public Result checkPassword(@RequestBody SysUserUpdatePasswordREQ req) {
        return userService.checkPassword(req);
    }

    @ApiOperation("更新用户密码")
    @PostMapping("/password")
    public Result updatePassword(@RequestBody SysUserUpdatePasswordREQ req) {
        return userService.updatePassword(req);
    }

    @ApiOperation("更新用户信息")
    @PutMapping
    public Result update(@RequestBody SysUser sysUser) {
        return userService.update(sysUser);
    }

    @ApiOperation("统计用户总数")
    @GetMapping("/total")
    public Result userTotal() {
        return userService.getUserTotal();
    }

}
