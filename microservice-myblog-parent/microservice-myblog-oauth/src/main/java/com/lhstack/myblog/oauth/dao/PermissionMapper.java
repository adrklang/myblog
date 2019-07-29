package com.lhstack.myblog.oauth.dao;

import com.lhstack.myblog.model.ucenter.BlogPermission;

import java.util.List;

public interface PermissionMapper {
    List<BlogPermission> findByIdPermissions(Long roleId);
}
