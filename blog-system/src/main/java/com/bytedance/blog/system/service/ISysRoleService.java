package com.bytedance.blog.system.service;

import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.system.req.SysRoleREQ;
import com.bytedance.blog.api.entities.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 根据角色名称查询角色
     * @param req 角色通用查询对象
     * @return 通用返回结果
     */
    Result queryPage(SysRoleREQ req);

    /**
     * 根据角色id删除角色菜单关系表
     * @param id 角色id
     * @return 通用返回值
     */
    Result deleteById(String id);

    /**
     * 根据角色id查询该角色拥有的菜单id列表
     * @param id 角色id
     * @return 菜单id列表
     */
    Result findMenuIdsById(String id);

    Result saveRoleMenu(String roleId, List<String> menuIds);
}
