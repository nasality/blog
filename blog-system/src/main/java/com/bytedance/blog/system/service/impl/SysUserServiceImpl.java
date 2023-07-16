package com.bytedance.blog.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.blog.api.entities.SysUser;
import com.bytedance.blog.api.feign.IFeignQuestionController;
import com.bytedance.blog.system.req.RegisterREQ;
import com.bytedance.blog.system.req.SysUserREQ;
import com.bytedance.blog.system.service.ISysUserService;
import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.api.entities.UserInfoREQ;
import com.bytedance.blog.api.feign.IFeignArticleController;
import com.bytedance.blog.system.req.SysUserCheckPasswordREQ;
import com.bytedance.blog.system.req.SysUserUpdatePasswordREQ;
import com.bytedance.blog.system.mapper.SysUserMapper;
import com.bytedance.blog.util.enums.ResultEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public Result queryPage(SysUserREQ req) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(req.getUsername())) {
            wrapper.like("username", req.getUsername());
        }
        if (StringUtils.isNotEmpty(req.getMobile())) {
            wrapper.like("mobile", req.getMobile());
        }
        wrapper.orderByDesc("update_date");

        return Result.ok(baseMapper.selectPage(req.getPage(), wrapper));
    }

    @Override
    public Result findRoleIdsById(String id) {
        return Result.ok(baseMapper.findRoleIdsById(id));
    }

    @Transactional
    @Override
    public Result saveUserRole(String userId, List<String> roleIds) {
        baseMapper.deleteUserRoleByUserId(userId);
        if (CollectionUtils.isNotEmpty(roleIds)) {
            baseMapper.saveUserRole(userId, roleIds);
        }
        return Result.ok();
    }

    @Override
    public Result deleteById(String id) {
        //根据id查找用户
        SysUser sysUser = baseMapper.selectById(id);
        if (sysUser == null) {
            return Result.error(ResultEnum.USER_NOT_EXIST.getDesc());
        }
        //删除
        sysUser.setIsEnabled(0);
        sysUser.setUpdateDate(new Date());
        baseMapper.updateById(sysUser);
        return Result.ok();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Result checkPassword(SysUserCheckPasswordREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("用户ID不能为空");
        }
        if (StringUtils.isEmpty(req.getOldPassword())) {
            return Result.error("密码不能为空");
        }
        SysUser sysUser = baseMapper.selectById(req.getId());
        if (sysUser == null) {
            return Result.error(ResultEnum.USER_NOT_EXIST.getDesc());
        }
        boolean matches = passwordEncoder.matches(req.getOldPassword(), sysUser.getPassword());
        return matches ? Result.ok() : Result.error("密码错误");
    }

    @Override
    public Result updatePassword(SysUserUpdatePasswordREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("用户ID不能为空");
        }
        if (StringUtils.isEmpty(req.getOldPassword())) {
            return Result.error("原始密码不能为空");
        }
        if (!req.getNewPassword().equals(req.getRepPassword())) {
            return Result.error("两次密码不一致，请重新输入");
        }
        SysUser sysUser = baseMapper.selectById(req.getId());
        if (sysUser == null) {
            return Result.error(ResultEnum.USER_NOT_EXIST.getDesc());
        }
        if (StringUtils.isNotEmpty(req.getOldPassword())) {
            if (!passwordEncoder.matches(req.getOldPassword(), sysUser.getPassword())) {
                return Result.error("原密码输入错误");
            }
        }
        //通过后将新密码加密存储
        sysUser.setPassword(passwordEncoder.encode(req.getNewPassword()));
        return Result.ok();
    }

    @Autowired
    private IFeignQuestionController feignQuestionController;
    @Autowired
    private IFeignArticleController feignArticleController;

    @Transactional
    @Override
    public Result update(SysUser sysUser) {
        //查询用户信息
        SysUser user = baseMapper.selectById(sysUser.getId());
        //判断更新的信息中昵称和头像是否有改变
        if (!StringUtils.equals(sysUser.getNickName(), user.getNickName()) ||
                StringUtils.equals(sysUser.getImageUrl(), user.getImageUrl())
        ) {
            //更新文章和问答微服务信息
            UserInfoREQ userInfoREQ = new UserInfoREQ(sysUser.getId(), sysUser.getNickName(), sysUser.getImageUrl());
            feignArticleController.updateUserInfo(userInfoREQ);
            feignQuestionController.updateUserInfo(userInfoREQ);
        }
        //更新sys_user
        sysUser.setUpdateDate(new Date());
        baseMapper.updateById(sysUser);
        return Result.ok();
    }

    @Override
    public Result getUserTotal() {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("is_enabled", 1);
        return Result.ok(baseMapper.selectCount(wrapper));
    }

    @Override
    public Result checkUsername(String name) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("user", name);
        SysUser sysUser = baseMapper.selectOne(wrapper);
        return Result.ok(sysUser != null);
    }

    @Override
    public Result register(RegisterREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            return Result.error("用户名不能为空");
        }
        if (StringUtils.isEmpty(req.getPassword())) {
            return Result.error("用户名不能为空");
        }
        if (StringUtils.isEmpty(req.getRepPassword())) {
            return Result.error("用户名不能为空");
        }

        if (!StringUtils.equals(req.getPassword(), req.getRepPassword())) {
            return Result.error("两次密码不一致");
        }

        SysUser sysUser = new SysUser();
        sysUser.setUsername(req.getUsername());
        sysUser.setNickName(req.getUsername());
        sysUser.setPassword(passwordEncoder.encode(req.getPassword()));
        this.save(sysUser);
        return Result.ok();
    }

    @Override
    public SysUser findByUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return baseMapper.selectOne(wrapper);
    }
}
