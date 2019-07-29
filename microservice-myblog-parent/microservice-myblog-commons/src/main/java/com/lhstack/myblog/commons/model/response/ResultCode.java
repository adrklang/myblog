package com.lhstack.myblog.commons.model.response;

public interface ResultCode {
    Boolean success();
    Integer code();
    String message();
}
