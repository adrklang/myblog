package com.lhstack.myblog.oauth.config;

import com.lhstack.myblog.model.ucenter.BlogUserService;
import com.lhstack.myblog.oauth.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

//自定义登录认证
@Component
public class Oauth2AuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String)authentication.getCredentials();
        BlogUserService blogUserService = (BlogUserService) userDetailsService.loadUserByUsername(username);
        if(blogUserService == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        String salt = blogUserService.getBlogUser().getSalt();
        if(!new BCryptPasswordEncoder().matches(salt + password,blogUserService.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = blogUserService.getAuthorities();
        return new UsernamePasswordAuthenticationToken(blogUserService, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
