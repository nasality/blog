package com.bytedance.blog.system.controller;


import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.system.req.SysMenuREQ;
import com.bytedance.blog.api.entities.SysMenu;
import com.bytedance.blog.system.service.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 菜单信息表 前端控制器
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
@RestController
@RequestMapping("/sys-menu")
@Api(value = "菜单管理接口", description = "菜单管理，提供增删改查等功能")
public class SysMenuController {
    @Autowired
    private ISysMenuService sysMenuService;

    @ApiOperation("根据菜单名称查询接口列表")
    @PostMapping("/search")
    public Result search(@RequestBody SysMenuREQ req) {
        return sysMenuService.queryList(req);
    }

    @ApiOperation("根据菜单名称查询接口列表")
    @ApiImplicitParam(name = "id", value = "菜单id", required = true)
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return sysMenuService.deleteById(id);
    }

    @ApiOperation("新增菜单信息接口")
    @PostMapping
    public Result save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return Result.ok();
    }

    @ApiOperation("查询菜单接口")
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id) {
        return Result.ok(sysMenuService.getById(id));
    }

    @ApiOperation("更新菜单接口")
    @PutMapping
    public Result update(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateDate(new Date());
        sysMenuService.updateById(sysMenu);
        return Result.ok();
    }

    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    @ApiOperation("通过用户id查询所拥有的权限菜单树和按钮")
    public Result findUserMenuTreeAndButton(@PathVariable("userId") String userId) {
        return sysMenuService.findUserMenuTree(userId);
    }
}
