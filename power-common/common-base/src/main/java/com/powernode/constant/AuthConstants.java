package com.powernode.constant;

/**
 * 认证授权常量类
 */
public interface AuthConstants {

    /**
     * 在请求头冲存放token值的前缀key
     */
    String AUTHORIZATION = "Authorization";
    /**
     * 存放token值的前缀
     */
    String BERAER = "bearer";

    /**
     * token值存放再redis中的前缀
     */
    String LOGIN_TOKEN_PREFIX = "login_token:";
}
