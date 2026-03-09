package com.example.usermanagement.service.impl;

import com.example.usermanagement.entity.Role;
import com.example.usermanagement.entity.UserRole;
import com.example.usermanagement.mapper.RoleMapper;
import com.example.usermanagement.mapper.UserRoleMapper;
import com.example.usermanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    @Override
    public List<Role> findByUserId(Integer userId) {
        return roleMapper.findByUserId(userId);
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        if (role.getName() == null || role.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("角色名称不能为空");
        }
        if (role.getCode() == null || role.getCode().trim().isEmpty()) {
            throw new IllegalArgumentException("角色编码不能为空");
        }
        if (role.getStatus() == null) {
            role.setStatus(1);
        }
        roleMapper.insert(role);
    }

    @Override
    @Transactional
    public void updateRole(Role role) {
        if (role.getId() == null) {
            throw new IllegalArgumentException("角色ID不能为空");
        }
        roleMapper.update(role);
    }

    @Override
    @Transactional
    public void deleteRole(Integer id) {
        roleMapper.delete(id);
    }

    @Override
    @Transactional
    public void assignRoles(Integer userId, Integer[] roleIds) {
        userRoleMapper.deleteByUserId(userId);
        if (roleIds != null && roleIds.length > 0) {
            for (Integer roleId : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoleMapper.insert(userRole);
            }
        }
    }
}
