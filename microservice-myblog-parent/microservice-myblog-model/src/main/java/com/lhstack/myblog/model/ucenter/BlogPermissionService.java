package com.lhstack.myblog.model.ucenter;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
@Data
@Accessors(chain = true)
public class BlogPermissionService implements GrantedAuthority{
    private BlogPermission blogPermission;
    @Override
    public String getAuthority() {
        return blogPermission.getPermissionName();
    }
}
