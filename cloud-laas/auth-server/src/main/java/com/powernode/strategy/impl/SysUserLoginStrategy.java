package com.powernode.strategy.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.powernode.constant.AuthConstants;
import com.powernode.domain.LoginSysUser;
import com.powernode.mapper.LoginSysUserMapper;
import com.powernode.model.SecurityUser;
import com.powernode.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * 商城后台登录系统登录策略
 */
@Service(AuthConstants.SYS_USER_LOGIN)
public class SysUserLoginStrategy implements LoginStrategy {
    @Autowired
    private LoginSysUserMapper loginSysUserMapper;

    @Override
    public UserDetails realLogin(String username) {
        //根据用户名称查询数据库
        LoginSysUser loginSysUser = loginSysUserMapper.selectOne(new LambdaQueryWrapper<LoginSysUser>()
                .eq(LoginSysUser::getUsername, username)
        );
        if (ObjectUtil.isNotNull(loginSysUser)) {
            //根据用户表示查询用户权限集合
            Set<String> perms = loginSysUserMapper.selectpermsByUserId(loginSysUser.getUserId());
            // 创建安全用户对象SecurityUser
            SecurityUser securityUser = new SecurityUser();
            securityUser.setUserId(loginSysUser.getUserId());
            securityUser.setPassword(loginSysUser.getPassword());
            securityUser.setShopId(loginSysUser.getShopId());
            securityUser.setStatus(loginSysUser.getStatus());
            securityUser.setLoginType(AuthConstants.LOGIN_TYPE);
            //判断用户权限是否有值
            if (CollectionUtil.isNotEmpty(perms)&&perms.size()!=0){
                securityUser.setPerms(perms);
            }
            return securityUser;
        }
        return null;
    }
}
