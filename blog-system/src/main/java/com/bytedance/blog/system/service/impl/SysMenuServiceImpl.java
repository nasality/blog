package com.bytedance.blog.system.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bytedance.blog.api.entities.SysMenu;
import com.bytedance.blog.util.base.Result;
import com.bytedance.blog.system.req.SysMenuREQ;
import com.bytedance.blog.system.mapper.SysMenuMapper;
import com.bytedance.blog.system.service.ISysMenuService;
import com.bytedance.blog.util.enums.ResultEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 菜单信息表 服务实现类
 * </p>
 *
 * @author 似水流年
 * @since 2023-07-09
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public Result queryList(SysMenuREQ req) {
        //根据条件查询出所有菜单
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(req.getName())) {
            wrapper.like("name", req.getName());
        }
        wrapper.orderByAsc("sort").orderByDesc("update_date");
        List<SysMenu> menuList = baseMapper.selectList(wrapper);
        return Result.ok(buildTree(menuList));
    }

    //两个delete
    @Transactional
    @Override
    public Result deleteById(String id) {
        //删除当前id的菜单
        baseMapper.deleteById(id);
        //删除当前id的子菜单
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysMenu::getParentId, id);
        baseMapper.delete(wrapper);
        return Result.ok();
    }

    @Override
    public Result findUserMenuTree(String userId) {
        List<SysMenu> menuList = baseMapper.findByUserId(userId);
        //新用户没被分配角色和目录
        if (CollectionUtils.isEmpty(menuList) || menuList.get(0) == null) {
            return Result.error(ResultEnum.MENU_NO.getDesc());
        }
        //获取集合中的目录和菜单放到一个集合中，按钮放到一个集合中

        //封装目录和菜单
        List<SysMenu> dirMenuList = new ArrayList<>();
        //封装按钮
        List<String> buttonList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menu.getType().equals(1) || menu.getType().equals(2)) {
                dirMenuList.add(menu);
            } else {
                buttonList.add(menu.getCode());
            }
        }
        //封装成树形结构
        List<SysMenu> menuTreeList = this.buildTree(dirMenuList);
        //响应
        Map<String, Object> data = new HashMap<>();
        data.put("menuTreeList", menuTreeList);
        data.put("buttonList", buttonList);
        return Result.ok(data);
    }

    @Override
    public List<SysMenu> findByUserId(String userId) {
        //通过用户查询权限
        List<SysMenu> menuList = baseMapper.findByUserId(userId);
        //当userid不存在的时候，menuList是空，存在但是又没分配权限时会有一条空记录
        if (CollectionUtils.isEmpty(menuList) || menuList.get(0) == null) {
            return null;
        }
        return menuList;
    }


    private List<SysMenu> buildTree(List<SysMenu> menuList) {
        //获取根菜单
        List<SysMenu> rootMenuList = new ArrayList<>();
        for (SysMenu menu : menuList) {
            if (menu.getParentId().equals("0")) {
                rootMenuList.add(menu);
            }
        }
        //根菜单下的子菜单
        for (SysMenu menu : rootMenuList) {
            childrenMenu(menuList, menu);
        }
        return rootMenuList;
    }

    /**
     * 判断父菜单ID是否等于所有菜单中的某一个菜单的parentId，如果等于，这个菜单是子菜单
     * @param menuList 菜单列表
     * @param menu 菜单
     * @return SysMenu
     */
    private SysMenu childrenMenu(List<SysMenu> menuList, SysMenu menu) {
        //存放menu菜单对象的所有子菜单
        List<SysMenu> children = new ArrayList<>();
        //循环所有菜单，第一次循环判断是否为根的子菜单
        for (SysMenu m : menuList) {
            if (m.getParentId().equals(menu.getId())) {

                children.add(childrenMenu(menuList,m));
            }
        }
        menu.setChildren(children);
        return menu;
    }
}
