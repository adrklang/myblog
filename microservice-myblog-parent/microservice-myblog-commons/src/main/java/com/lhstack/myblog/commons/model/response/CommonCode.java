package com.lhstack.myblog.commons.model.response;

import lombok.ToString;

@ToString
public enum CommonCode implements ResultCode {
    INVALID_PARAM(false,10003,"非法参数"),
    SUCCESS(true,10000,"操作成功！"),
    FAIL(false,11111,"操作失败！"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");

    private Boolean success;

    private Integer code;

    private String message;

    private CommonCode(Boolean success,Integer code,String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public Boolean success() {
        return success;
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
