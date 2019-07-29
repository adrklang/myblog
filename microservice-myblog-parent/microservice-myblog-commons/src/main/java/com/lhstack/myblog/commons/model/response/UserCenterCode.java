package com.lhstack.myblog.commons.model.response;

public enum  UserCenterCode implements ResultCode {
    SUCCESS(true,10000,"登录成功"),
    FAILD(false,99999,"用户名密码有误"),
    USER_EXSIS(false,99999,"该用户已存在"),
    USER_REGISTORY_SUCCESS(true,10000,"注册用户成功"),
    NULL_USER(false,10000,"系统用户"),
    LOGOUT_SUCCESS(true,10000,"退出登录成功");
    private Boolean success;
    private Integer code;
    private String message;
    UserCenterCode(Boolean success,Integer code,String message){
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
