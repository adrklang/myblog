package com.lhstack.myblog.oauth.service.impl;

import com.lhstack.myblog.model.ucenter.BlogPermission;
import com.lhstack.myblog.oauth.dao.PermissionMapper;
import com.lhstack.myblog.oauth.service.IPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PermissionServiceImpl implements IPermissionService {

    private PermissionMapper permissionMapper;
    @Override
    public List<BlogPermission> findByIdPermissions(Long roleId) {
        return permissionMapper.findByIdPermissions(roleId);
    }
}
