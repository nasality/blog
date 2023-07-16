package com.bytedance.blog.system.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.blog.api.entities.SysRole;
import com.bytedance.blog.system.service.ISysRoleService;
import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.system.req.SysRoleREQ;
import com.bytedance.blog.system.mapper.SysRoleMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public Result queryPage(SysRoleREQ req) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(req.getName())) {
            wrapper.eq("name", req.getName());
        }
        IPage<SysRole> iPage = baseMapper.selectPage(req.getPage(), wrapper);
        return Result.ok(iPage);
    }

    @Override
    public Result deleteById(String id) {
        //删除角色表中的信息
        baseMapper.deleteById(id);
        //删除角色中间表信息
        baseMapper.deleteRoleMenuByRoleId(id);
        return Result.ok();
    }

    @Override
    public Result findMenuIdsById(String id) {
        return Result.ok(baseMapper.findMenuIdsById(id));
    }

    @Override
    public Result saveRoleMenu(String roleId, List<String> menuIds) {
        //删除已存在的角色菜单数据
        baseMapper.deleteRoleMenuByRoleId(roleId);
        //保存新的Ids
        if (CollectionUtils.isNotEmpty(menuIds)) {
            baseMapper.saveRoleMenu(roleId, menuIds);
        }
        return Result.ok();
    }
}
