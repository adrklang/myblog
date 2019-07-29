package com.lhstack.myblog.oauth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhstack.myblog.commons.model.response.ResponseResult;
import com.lhstack.myblog.commons.model.response.UserCenterCode;
import com.lhstack.myblog.oauth.handler.Oauth2LoginFailureHandler;
import com.lhstack.myblog.oauth.handler.Oauth2LoginSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true,proxyTargetClass = true)
@AllArgsConstructor
public class Oauth2SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Oauth2AuthenticationProvider oauth2AuthenticationProvider;

    private final Oauth2LoginSuccessHandler oauth2LoginSuccessHandler;

    private final Oauth2LoginFailureHandler oauth2LoginFailureHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(oauth2AuthenticationProvider);//自定义provider
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()//授权给那些请求
                .anyRequest()//所有请求
                .authenticated()//需要授权
                .and()
                .formLogin()//开启表单登录
                .successHandler(oauth2LoginSuccessHandler)//登录成功处理器
                .failureHandler(oauth2LoginFailureHandler)//登录失败处理器
                .loginProcessingUrl("/login")//执行登录的url地址
                .usernameParameter("username")//登录提交的username参数名，默认username
                .passwordParameter("password")//登录提交的password参数名，默认password
                .permitAll()//不需要认证
                .and()
                .logout()//退出登录
                .clearAuthentication(true)//清除权限认证
                .invalidateHttpSession(true)//清除session
                .logoutSuccessHandler((HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) ->{
                    ResponseResult responseResult = new ResponseResult(UserCenterCode.LOGOUT_SUCCESS);
                    new ObjectMapper().writeValue(httpServletResponse.getOutputStream(),responseResult);
                })
                .permitAll()//不需要认证
                .and()
                .csrf()//关闭功能,方便测试,比如使用postMan
                .disable();//
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/actuator/**");//将匹配的路径不由security管理
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();//授权管理器
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
