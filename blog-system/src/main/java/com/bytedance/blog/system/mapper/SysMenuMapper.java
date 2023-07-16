package com.bytedance.blog.system.mapper;

import com.bytedance.blog.api.entities.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单信息表 Mapper 接口
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户和角色，构建所属的所有菜单权限
     */

    List<SysMenu> findByUserId(@Param("userId")String userId);
}
