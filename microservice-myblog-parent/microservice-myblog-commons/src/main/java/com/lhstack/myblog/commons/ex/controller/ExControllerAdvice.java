package com.lhstack.myblog.commons.ex.controller;

import com.lhstack.myblog.commons.ex.NullParameterException;
import com.lhstack.myblog.commons.ex.RegistryException;
import com.lhstack.myblog.commons.ex.ValidCodeException;
import com.lhstack.myblog.commons.model.response.CommonCode;
import com.lhstack.myblog.commons.model.response.ResponseResult;
import com.lhstack.myblog.commons.model.response.UserCenterCode;
import com.lhstack.myblog.commons.model.response.ValidCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExControllerAdvice {
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseResult usernameNotFoundExHandler(AuthenticationException ex){
        ex.printStackTrace();
        return new ResponseResult(UserCenterCode.FAILD);
    }
    @ExceptionHandler(RegistryException.class)
    @ResponseBody
    public ResponseResult registryExHandler(RegistryException ex){
        Integer type = ex.getType();
        switch (type){
            case 0:return new ResponseResult(CommonCode.INVALID_PARAM);
            case 1:return new ResponseResult(UserCenterCode.USER_EXSIS);
        }
        return new ResponseResult(UserCenterCode.FAILD);
    }
    @ExceptionHandler(ValidCodeException.class)
    @ResponseBody
    public ResponseResult validCodeExHandler(ValidCodeException ex){
        Integer type = ex.getType();
        switch (type){
            case 1: return new ResponseResult(ValidCode.VALID_IS_NULL);
            case 2: return new ResponseResult(ValidCode.VALID_IS_FAILD);
            case 3: return new ResponseResult(ValidCode.VALID_NOT_EXSIS);
        }
        return null;
    }
}
