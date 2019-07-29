package com.lhstack.myblog.commons.model.response;

public enum LimitCode implements ResultCode{
    LIMIT_SUCCESS(false,99999,"限流中，请稍后重试");
    private Boolean success;

    private Integer code;

    private String message;
    private LimitCode(Boolean success,Integer code,String message){
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
