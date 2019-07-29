package com.lhstack.myblog.oauth.dao;

import com.lhstack.myblog.model.ucenter.BlogUser;

public interface UserMapper {
    BlogUser findByUsername(String username);
}
