package com.bytedance.blog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.blog.api.entities.SysMenu;
import com.bytedance.blog.system.req.SysMenuREQ;
import com.bytedance.blog.util.base.Result;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 根据菜单名称查询菜单列表
     */
    Result queryList(SysMenuREQ req);

    /**
     * 根据菜单id删除
     */
    Result deleteById(String id);

    /**
     * 根据用户及角色，构建所属的所有菜单权限
     */
    Result findUserMenuTree(String userId);

    /**
     * 通过用户id查询用户权限
     */
    List<SysMenu> findByUserId(String userId);
}
