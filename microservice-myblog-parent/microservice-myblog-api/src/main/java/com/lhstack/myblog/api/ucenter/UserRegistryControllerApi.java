package com.lhstack.myblog.api.ucenter;

import com.lhstack.myblog.model.ucenter.RegistryModel;
import com.lhstack.myblog.model.ucenter.response.BlogUserResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Api(value = "用户注册接口",description = "只提供用户注册功能")
public interface UserRegistryControllerApi {
    @ApiOperation("用户注册接口")
    BlogUserResult registry(RegistryModel registryModel, String validCode, @ApiIgnore HttpSession httpSession);
    @ApiOperation("用户验证码接口")
    void validCode(@ApiIgnore HttpServletResponse response,@ApiIgnore HttpSession httpSession) throws Exception;
}
