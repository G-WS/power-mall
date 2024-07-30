package com.powernode.controller;

import com.powernode.util.AuthUtils;
import com.powernode.domain.SysMenu;
import com.powernode.model.Result;
import com.powernode.model.SecurityUser;
import com.powernode.service.SysMenuService;
import com.powernode.vo.MenuAndAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 系统权限控制层
 */
@Api(tags = "系统权限接口管理")
@RequestMapping("sys/menu")
@RestController
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 查询用户的菜单权限和操作权限
     * @return
     */
//    sys/menu/nav
    @ApiOperation("查询用户的菜单权限和操作权限")
    @GetMapping("nav")
    public Result<MenuAndAuth> loadUserMenuAndAuth() {
        // 获取当前登录用户的标识
//        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Long userId = securityUser.getUserId();
        Long loginUserId = AuthUtils.getLoginUserId();

        // 根据用户标识查询操作权限集合
        Set<String> perms = AuthUtils.getLoginUserPerms();
        // 根据用户标识查询菜单权限集合
        Set<SysMenu> menus = sysMenuService.queryUserMenuListByUserId(loginUserId);

        // 创建菜单和操作权限对象
        MenuAndAuth menuAndAuth = new MenuAndAuth(menus,perms);
        return Result.success(menuAndAuth);
    }

}
