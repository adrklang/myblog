package com.lhstack.myblog.registry.service;

import com.lhstack.myblog.model.ucenter.BlogUser;
import com.lhstack.myblog.model.ucenter.response.BlogUserResult;

public interface IUserService {
    BlogUserResult registry(BlogUser blogUser);
}
