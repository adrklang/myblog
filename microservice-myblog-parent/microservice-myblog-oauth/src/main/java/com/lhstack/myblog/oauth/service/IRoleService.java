package com.lhstack.myblog.oauth.service;

import com.lhstack.myblog.model.ucenter.BlogRole;

public interface IRoleService {
    BlogRole findByRoleId(Long id);
}
