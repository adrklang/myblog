package com.lhstack.myblog.oauth.dao;

import com.lhstack.myblog.model.ucenter.BlogRole;

public interface RoleMapper {
    BlogRole findByRoleId(Long id);
}
