package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.PageParam;
import com.example.usermanagement.dto.PageResult;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.mapper.UserMapper;
import com.example.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public PageResult<User> findByPage(PageParam param) {
        Long current = param.getCurrent();
        Long size = param.getSize();
        String keyword = param.getKeyword();
        
        Long offset = (current - 1) * size;
        List<User> records = userMapper.findByPage(keyword, offset, size);
        Long total = userMapper.countByPage(keyword);
        
        return PageResult.of(records, total, current, size);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (user.getAge() != null && user.getAge() < 0) {
            throw new IllegalArgumentException("年龄不能为负数");
        }
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        if (user.getStatus() == null) {
            user.setStatus(1);
        }
        if (user.getDelFlag() == null) {
            user.setDelFlag(0);
        }
        userMapper.insert(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        if (user.getId() == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }
        User existingUser = userMapper.findById(user.getId());
        if (existingUser == null) {
            throw new RuntimeException("用户不存在");
        }
        userMapper.update(user);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userMapper.delete(id);
    }
}