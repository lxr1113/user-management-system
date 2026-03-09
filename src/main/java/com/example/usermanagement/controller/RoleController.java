package com.example.usermanagement.controller;

import com.example.usermanagement.dto.Result;
import com.example.usermanagement.entity.Role;
import com.example.usermanagement.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取角色列表")
    @GetMapping
    public Result<List<Role>> list() {
        return Result.success(roleService.findAll());
    }

    @ApiOperation("根据ID获取角色")
    @GetMapping("/{id}")
    public Result<Role> getById(@ApiParam("角色ID") @PathVariable Integer id) {
        return Result.success(roleService.findById(id));
    }

    @ApiOperation("获取用户角色")
    @GetMapping("/user/{userId}")
    public Result<List<Role>> getByUserId(@ApiParam("用户ID") @PathVariable Integer userId) {
        return Result.success(roleService.findByUserId(userId));
    }

    @ApiOperation("新增角色")
    @PostMapping
    public Result<String> add(@RequestBody Role role) {
        roleService.addRole(role);
        return Result.success("添加成功");
    }

    @ApiOperation("更新角色")
    @PutMapping("/{id}")
    public Result<String> update(@ApiParam("角色ID") @PathVariable Integer id, @RequestBody Role role) {
        role.setId(id);
        roleService.updateRole(role);
        return Result.success("修改成功");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public Result<String> delete(@ApiParam("角色ID") @PathVariable Integer id) {
        roleService.deleteRole(id);
        return Result.success("删除成功");
    }

    @ApiOperation("分配角色")
    @PostMapping("/assign")
    public Result<String> assignRoles(@ApiParam("用户ID") @RequestParam Integer userId, 
                                       @ApiParam("角色ID列表") @RequestParam Integer[] roleIds) {
        roleService.assignRoles(userId, roleIds);
        return Result.success("分配成功");
    }
}
