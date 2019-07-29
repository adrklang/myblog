package com.lhstack.myblog.oauth.service;

import com.lhstack.myblog.model.ucenter.BlogPermission;
import com.lhstack.myblog.model.ucenter.BlogPermissionService;
import com.lhstack.myblog.model.ucenter.BlogUser;
import com.lhstack.myblog.model.ucenter.BlogUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private IUserService userService;
    private IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BlogUser blogUser = userService.findByUsername(username);//查询登陆用户
        if(blogUser == null)
            throw new UsernameNotFoundException("用户不存在");
        List<BlogPermission> list = permissionService.findByIdPermissions(blogUser.getRoleId());//查询权限
        BlogUserService blogUserService = new BlogUserService();//封装成userDetailsService对象
        List<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        list.forEach(item ->{
            BlogPermissionService blogPermissionService = new BlogPermissionService();
            blogPermissionService.setBlogPermission(item);
            grantedAuthorities.add(blogPermissionService);
        });
        blogUserService.setBlogUser(blogUser)
                .setList(grantedAuthorities);
        String password = blogUserService.getPassword();
        return blogUserService;
    }
}
