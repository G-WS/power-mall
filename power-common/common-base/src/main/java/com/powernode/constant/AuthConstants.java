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
    String BERAER = "bearer ";

    /**
     * token值存放再redis中的前缀
     */
    String LOGIN_TOKEN_PREFIX = "login_token:";
    /**
     * 登录URL
     */
    String LOGIN_URL = "/doLogin";
    /**
     * 登出URL
     */
    String LOGOUT_URL = "/doLogout";

    /**
     *登录类型
     */
    String LOGIN_TYPE = "loginType";
    /**
     * 登录类型值，商城后台管理系统用户
     */
    String SYS_USER_LOGIN = "sysUserLogin";
    /**
     * 登录类型值，上称用户购物系统
     */
    String MEMBER_LOGIN = "memberLogin";
    /**
     * TOKEN有效时长，单位为秒，四个小时
     */
    Long TOKEN_TIME=14400L;

    /**
     *TOKEN的阈值：3600秒
     */
    Long TOKEN_EXPIRE_THRESHOLD_TIME=60*60L;
}
