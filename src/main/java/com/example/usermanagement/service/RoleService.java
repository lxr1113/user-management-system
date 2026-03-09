package com.example.usermanagement.service;

import com.example.usermanagement.entity.Role;
import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findById(Integer id);
    List<Role> findByUserId(Integer userId);
    void addRole(Role role);
    void updateRole(Role role);
    void deleteRole(Integer id);
    void assignRoles(Integer userId, Integer[] roleIds);
}
