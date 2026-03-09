package com.example.usermanagement.controller;

import com.example.usermanagement.dto.PageParam;
import com.example.usermanagement.dto.PageResult;
import com.example.usermanagement.dto.Result;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Result<List<User>> list() {
        return Result.success(userService.findAll());
    }

    @GetMapping("/page")
    public Result<PageResult<User>> page(PageParam param) {
        return Result.success(userService.findByPage(param));
    }

    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Integer id) {
        return Result.success(userService.findById(id));
    }

    @GetMapping("/search")
    public Result<User> getByUsername(@RequestParam String username) {
        return Result.success(userService.findByUsername(username));
    }

    @PostMapping
    public Result<String> add(@RequestBody User user) {
        userService.addUser(user);
        return Result.success("添加成功，ID：" + user.getId());
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return Result.success("修改成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return Result.success("删除成功");
    }
}