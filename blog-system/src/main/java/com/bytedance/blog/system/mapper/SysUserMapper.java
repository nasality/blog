package com.bytedance.blog.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bytedance.blog.api.entities.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<String> findRoleIdsById(@Param("id") String id);

    Boolean deleteUserRoleByUserId(@Param("userId") String userId);

    Boolean saveUserRole(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);
}
