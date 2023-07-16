package com.bytedance.blog.system.feign;

import com.bytedance.blog.api.entities.SysMenu;
import com.bytedance.blog.api.entities.SysUser;
import com.bytedance.blog.api.feign.IFeignSystemController;
import com.bytedance.blog.system.service.ISysMenuService;
import com.bytedance.blog.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignSystemController implements IFeignSystemController {
    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;
    @Override
    public SysUser findUserByUsername(String username) {
        return sysUserService.findByUsername(username);
    }

    @Override
    public List<SysMenu> findMenuByUserId(String userId) {
        return sysMenuService.findByUserId(userId);
    }
}
