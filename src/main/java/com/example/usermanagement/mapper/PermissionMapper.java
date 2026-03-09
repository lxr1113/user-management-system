package com.example.usermanagement.mapper;

import com.example.usermanagement.entity.Permission;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface PermissionMapper {

    @Select("SELECT * FROM permission ORDER BY sort")
    List<Permission> findAll();

    @Select("SELECT * FROM permission WHERE id = #{id}")
    Permission findById(Integer id);

    @Select("SELECT p.* FROM permission p " +
            "INNER JOIN role_permission rp ON p.id = rp.permission_id " +
            "INNER JOIN user_role ur ON rp.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<Permission> findByUserId(Integer userId);

    @Insert("INSERT INTO permission(name, code, type, url, parent_id, sort) VALUES(#{name}, #{code}, #{type}, #{url}, #{parentId}, #{sort})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Permission permission);

    @Update("UPDATE permission SET name=#{name}, code=#{code}, type=#{type}, url=#{url}, parent_id=#{parentId}, sort=#{sort} WHERE id=#{id}")
    int update(Permission permission);

    @Delete("DELETE FROM permission WHERE id = #{id}")
    int delete(Integer id);
}
