package com.bytedance.blog.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bytedance.blog.api.entities.SysUser;
import com.bytedance.blog.system.req.SysUserREQ;
import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.system.req.RegisterREQ;
import com.bytedance.blog.system.req.SysUserCheckPasswordREQ;
import com.bytedance.blog.system.req.SysUserUpdatePasswordREQ;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
public interface ISysUserService extends IService<SysUser> {
    Result queryPage(SysUserREQ req);

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    Result findRoleIdsById(String id);

    /**
     * 新增角色
     * @param userId 用户id
     * @param roleIds 角色列表
     * @return 通用返回结果
     */
    Result saveUserRole(String userId, List<String> roleIds);

    /**
     * 根据id删除角色
     * @param id 角色id
     * @return 通用返回结果
     */
    Result deleteById(String id);

    /**
     * 密码校验
     * @param req
     */
    Result checkPassword(SysUserCheckPasswordREQ req);

    /**
     * 密码修改
     * @param req
     * @return
     */
    Result updatePassword(SysUserUpdatePasswordREQ req);

    /**
     *
     */
    Result update(SysUser sysUser);

    /**
     * 统计用户总数
     */
    Result getUserTotal();

    /**
     * 检查用户名是否被注册
     */
    Result checkUsername(String name);

    /**
     * 用户注册
     * @param req
     * @return
     */
    Result register(RegisterREQ req);

    /**
     * 通过用户名查询用户信息
     */
    SysUser findByUsername(String username);
}
