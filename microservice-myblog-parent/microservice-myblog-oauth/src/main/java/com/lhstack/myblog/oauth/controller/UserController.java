package com.lhstack.myblog.oauth.controller;

import com.lhstack.myblog.api.ucenter.UserControllerApi;
import com.lhstack.myblog.commons.model.response.CommonCode;
import com.lhstack.myblog.commons.model.response.ResponseResult;
import com.lhstack.myblog.model.ucenter.BlogPermission;
import com.lhstack.myblog.model.ucenter.BlogRole;
import com.lhstack.myblog.model.ucenter.BlogUser;
import com.lhstack.myblog.model.ucenter.BlogUserService;
import com.lhstack.myblog.model.ucenter.response.BlogUserServiceResult;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController implements UserControllerApi {
    private AuthenticationManager authenticationManager;
    @GetMapping("info")
    @PreAuthorize("@pm.hasPermission('microservice-myblog-info')")
    public ResponseResult info(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if(principal == null)
            throw new UsernameNotFoundException("请登录");
        BlogUserServiceResult blogUserServiceResult = new BlogUserServiceResult(CommonCode.SUCCESS, (BlogUserService) principal);
        return blogUserServiceResult;
    }

    @Override
    public BlogUser save(BlogUser blogUser) {
        return null;
    }

    @Override
    public BlogRole save(BlogRole blogRole) {
        return null;
    }

    @Override
    public BlogPermission save(BlogPermission blogPermission) {
        return null;
    }
}
