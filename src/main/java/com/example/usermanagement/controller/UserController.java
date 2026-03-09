package com.example.usermanagement.controller;

import com.example.usermanagement.dto.PageParam;
import com.example.usermanagement.dto.PageResult;
import com.example.usermanagement.dto.Result;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户列表")
    @GetMapping
    public Result<List<User>> list() {
        return Result.success(userService.findAll());
    }

    @ApiOperation("分页查询用户")
    @GetMapping("/page")
    public Result<PageResult<User>> page(PageParam param) {
        return Result.success(userService.findByPage(param));
    }

    @ApiOperation("根据ID获取用户")
    @GetMapping("/{id}")
    public Result<User> getById(@ApiParam("用户ID") @PathVariable Integer id) {
        return Result.success(userService.findById(id));
    }

    @ApiOperation("根据用户名查询")
    @GetMapping("/search")
    public Result<User> getByUsername(@ApiParam("用户名") @RequestParam String username) {
        return Result.success(userService.findByUsername(username));
    }

    @ApiOperation("新增用户")
    @PostMapping
    public Result<String> add(@RequestBody User user) {
        userService.addUser(user);
        return Result.success("添加成功，ID：" + user.getId());
    }

    @ApiOperation("更新用户")
    @PutMapping("/{id}")
    public Result<String> update(@ApiParam("用户ID") @PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
        return Result.success("修改成功");
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public Result<String> delete(@ApiParam("用户ID") @PathVariable Integer id) {
        userService.deleteUser(id);
        return Result.success("删除成功");
    }
}