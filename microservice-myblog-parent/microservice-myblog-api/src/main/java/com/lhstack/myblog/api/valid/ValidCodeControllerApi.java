package com.lhstack.myblog.api.valid;

import com.lhstack.myblog.commons.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Api(value = "验证码接口",description = "生成图片验证码和验证图片验证码")
public interface ValidCodeControllerApi {
    @ApiOperation("生成验证码")
    void createValid(@ApiIgnore HttpSession session,@ApiIgnore  HttpServletResponse response) throws IOException;
    @ApiOperation("验证验证码")
    ResponseResult validCode(@ApiIgnore HttpSession session,String codeStr);
}
