package com.lhstack.myblog.oauth.service;

import com.lhstack.myblog.model.ucenter.BlogUser;

public interface IUserService {
    BlogUser findByUsername(String username);
}
