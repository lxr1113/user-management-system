package com.example.usermanagement.service;

import com.example.usermanagement.dto.PageParam;
import com.example.usermanagement.dto.PageResult;
import com.example.usermanagement.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    User findByUsername(String username);
    PageResult<User> findByPage(PageParam param);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
}