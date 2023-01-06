package com.study.hunting.vo;

import com.study.hunting.enums.ResponseCode;

public class ResultVO<T> {
    public ResultVO() {
    }

    public int getCode() {
        return code;
    }

    private int code;
    private String msg;
    private T data;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        setResponseCode(ResponseCode.SUCCESS); // 默认成功
    }

    public void setResponseCode(ResponseCode responseCode) {
        code = responseCode.getCode();
        msg = responseCode.getMsg();
    }
}
