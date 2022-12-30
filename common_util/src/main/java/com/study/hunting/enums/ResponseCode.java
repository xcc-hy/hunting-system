package com.study.hunting.enums;

public enum ResponseCode {

    SUCCESS(1, "成功"),
    FAIL(0, "失败"),
    EMAIL_ERROR(1000, "用户名不存在"),
    PASSWORD_ERROR(1001, "密码错误"),
    ACCOUNT_FREEZE(1002, "账户冻结，请联系管理员了解详情！"),
    REGISTRATION_ERROR(1003, "用户已注册"),
    USER_NAME_ILLEGAL(1004, "用户名不合法"),
    UNKNOWN_ERROR(2000, "未知错误"),
    PARAMETER_ILLEGAL(2001, "参数不合法"),
    TOKEN_INVALID(2002, "无效的token"),
    PRIMARY_ID_ERROR(2003, "条目不存在"),
    TOKEN_MISSION(2005, "token缺失"),
    REFRESH_TOKEN_INVALID(2006, "刷新token无效"),
    NO_OPERATION_PERMISSION(2007, "没有操作权限");


    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
