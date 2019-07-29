package com.lhstack.myblog.commons.model.response;

public enum ValidCode implements ResultCode {
    VALID_IS_NULL(false,99999,"验证码为空"),
    VALID_IS_FAILD(false,99999,"验证码不正确，请重新输入"),
    VALID_NOT_EXSIS(true,99999,"验证码不存在，请先获取验证码");

    private Boolean success;
    private Integer code;
    private String message;
    ValidCode(Boolean success,Integer code,String message){
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
