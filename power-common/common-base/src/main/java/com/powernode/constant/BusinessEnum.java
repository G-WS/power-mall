package com.powernode.constant;

public enum BusinessEnum {

    OPERATION_FAIL(-1,"操作失败"),
    SERVER_INNER_ERROR(9999,"服务器内部异常"),
    UN_AUTHORIZATION(401,"未授权"),
    ACCESS_DENY_FAIL(403,"权限不足，请联系管理员")
    ;
    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    BusinessEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
