package com.powernode.strategy.impl;

import com.powernode.constant.AuthConstants;
import com.powernode.strategy.LoginStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * 商城购物系统登录具体实现策略
 */
@Service(AuthConstants.MEMBER_LOGIN)
public class MemberLoginStrategy implements LoginStrategy {
    @Override
    public UserDetails realLogin(String username) {
        return null;
    }
}
