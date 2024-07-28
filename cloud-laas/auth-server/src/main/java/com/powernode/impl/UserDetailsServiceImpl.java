package com.powernode.impl;

import com.powernode.constant.AuthConstants;
import com.powernode.factory.LoginStrategyFactory;
import com.powernode.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

/**
 * 项目自己的实现类
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private LoginStrategyFactory loginStrategyFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //从请求头中获取登录类型
        String loginType = request.getHeader(AuthConstants.LOGIN_TYPE);

        //判断请求来自哪一个系统

//        if (AuthConstants.SYS_USER_LOGIN.equals(loginType)){
//            //商城后台管理系统流程
//        }else {
//            //上称用户系统流程
//        }
        if (!StringUtils.hasText(loginType)){
            throw new InternalAuthenticationServiceException("非法登录，登录类型不匹配");
        }
        //通过登录策略工厂获取具体的登录策略对象
        LoginStrategy instance = loginStrategyFactory.getInstance(loginType);
        return instance.realLogin(username);

    }
}
