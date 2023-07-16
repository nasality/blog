package com.bytedance.blog.system.controller;


import com.bytedance.blog.system.req.SysRoleREQ;
import com.bytedance.blog.api.entities.SysRole;
import com.bytedance.blog.system.service.ISysRoleService;
import com.bytedance.blog.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */

@Api(value = "角色管理接口", description = "角色管理接口，提供增删改查功能")
@RestController
@RequestMapping("/sys-role")
public class SysRoleController {
    @Autowired
    private ISysRoleService sysRoleService;

    @ApiOperation("根据角色名查询角色列表接口")
    @PostMapping("/search")
    public Result search(@RequestBody SysRoleREQ req) {
        return sysRoleService.queryPage(req);
    }

    @ApiOperation("新增角色接口")
    @GetMapping("/{id}")
    public Result save(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return Result.ok();
    }

    @ApiOperation("根据id更新角色接口")
    @PutMapping
    public Result update(@RequestBody SysRole sysRole) {
        sysRole.setUpdateDate(new Date());
        sysRoleService.updateById(sysRole);
        return Result.ok();
    }

    @ApiOperation("根据角色id删除角色及关联接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return sysRoleService.deleteById(id);
    }

    @ApiOperation("根据角色id查询对应菜单id列表接口")
    @GetMapping("/{id}/menu/ids")
    public Result findMenuIdsById(@PathVariable("id") String id) {
        return sysRoleService.findMenuIdsById(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true),
            @ApiImplicitParam(allowMultiple = true, dataType = "string", name = "menuIds", value = "菜单id集合", required = true)
    })
    @ApiOperation("新增角色菜单关系接口")
    @PostMapping("/{id}/menu/save")
    public Result saveRoleMenu(@PathVariable("id") String id, @RequestBody List<String> menuIds) {
        return sysRoleService.saveRoleMenu(id, menuIds);
    }
}
