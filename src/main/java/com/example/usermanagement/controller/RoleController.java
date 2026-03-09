package com.example.usermanagement.controller;

import com.example.usermanagement.dto.Result;
import com.example.usermanagement.entity.Role;
import com.example.usermanagement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public Result<List<Role>> list() {
        return Result.success(roleService.findAll());
    }

    @GetMapping("/{id}")
    public Result<Role> getById(@PathVariable Integer id) {
        return Result.success(roleService.findById(id));
    }

    @GetMapping("/user/{userId}")
    public Result<List<Role>> getByUserId(@PathVariable Integer userId) {
        return Result.success(roleService.findByUserId(userId));
    }

    @PostMapping
    public Result<String> add(@RequestBody Role role) {
        roleService.addRole(role);
        return Result.success("添加成功");
    }

    @PutMapping("/{id}")
    public Result<String> update(@PathVariable Integer id, @RequestBody Role role) {
        role.setId(id);
        roleService.updateRole(role);
        return Result.success("修改成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return Result.success("删除成功");
    }

    @PostMapping("/assign")
    public Result<String> assignRoles(@RequestParam Integer userId, @RequestParam Integer[] roleIds) {
        roleService.assignRoles(userId, roleIds);
        return Result.success("分配成功");
    }
}
