package com.lhstack.myblog.oauth.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("pm")
@Slf4j
public class PermissionValidUtils {
    public boolean hasPermission(String permission) {
        log.info("权限验证中 : {}",permission);
        if(StringUtils.isEmpty(permission))
            return false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority)//将对象转换成字符串
                .filter(StringUtils::isNotEmpty)//判断权限是否为空
                .anyMatch(x -> x.equals(permission));//判断是否存在此权限
    }
}
