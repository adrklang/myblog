package com.lhstack.myblog.oauth.service.impl;

import com.lhstack.myblog.model.ucenter.BlogUser;
import com.lhstack.myblog.oauth.dao.UserMapper;
import com.lhstack.myblog.oauth.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private UserMapper userMapper;
    @Override
    public BlogUser findByUsername(String username) {
        if(username == null)
            throw new UsernameNotFoundException("用户名密码错误");
        return userMapper.findByUsername(username);
    }
}
