package com.lhstack.myblog.oauth.service;

import com.lhstack.myblog.model.ucenter.BlogPermission;

import java.util.List;

public interface IPermissionService {
    List<BlogPermission> findByIdPermissions(Long roleId);
}
