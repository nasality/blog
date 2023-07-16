package com.bytedance.blog.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bytedance.blog.api.entities.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 根据角色id删除角色菜单关系表
     * @param roleId 角色id
     * @return 布尔值
     */
    boolean deleteRoleMenuByRoleId(@Param("roleId") String roleId);

    /**
     * 根据角色id查询该角色拥有的菜单id列表
     * @param id 角色id
     * @return 菜单id列表
     */
    List<String> findMenuIdsById(@Param("id") String id);

    /**
     * 新增角色菜单权限
     * @param roleId 角色Id
     * @param menuIds 新增菜单列表
     * @return
     */
    boolean saveRoleMenu(@Param("roleId") String roleId, @PathVariable("menuIds") List<String> menuIds);
}
