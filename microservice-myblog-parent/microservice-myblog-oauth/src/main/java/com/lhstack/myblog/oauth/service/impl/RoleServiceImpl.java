package com.lhstack.myblog.oauth.service.impl;

import com.lhstack.myblog.model.ucenter.BlogRole;
import com.lhstack.myblog.oauth.dao.RoleMapper;
import com.lhstack.myblog.oauth.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class RoleServiceImpl implements IRoleService {

    private RoleMapper roleMapper;

    @Override
    public BlogRole findByRoleId(Long id) {
        return roleMapper.findByRoleId(id);
    }
}
