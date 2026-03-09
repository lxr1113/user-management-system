package com.example.usermanagement.mapper;

import com.example.usermanagement.entity.RolePermission;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RolePermissionMapper {

    @Insert("INSERT INTO role_permission(role_id, permission_id) VALUES(#{roleId}, #{permissionId})")
    int insert(RolePermission rolePermission);

    @Delete("DELETE FROM role_permission WHERE role_id = #{roleId}")
    int deleteByRoleId(Integer roleId);

    @Select("SELECT permission_id FROM role_permission WHERE role_id = #{roleId}")
    Integer[] findPermissionIdsByRoleId(Integer roleId);
}
