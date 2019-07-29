package com.lhstack.myblog.oauth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhstack.myblog.commons.model.response.UserCenterCode;
import com.lhstack.myblog.model.ucenter.BlogUserService;
import com.lhstack.myblog.model.ucenter.response.BlogUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Oauth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        BlogUserResult responseResult = new BlogUserResult(UserCenterCode.SUCCESS,((BlogUserService) authentication.getPrincipal()).getBlogUser());
        objectMapper.writeValue(httpServletResponse.getOutputStream(),responseResult);
    }
}
