package com.example.usermanagement.mapper;

import com.example.usermanagement.entity.UserRole;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserRoleMapper {

    @Insert("INSERT INTO user_role(user_id, role_id) VALUES(#{userId}, #{roleId})")
    int insert(UserRole userRole);

    @Delete("DELETE FROM user_role WHERE user_id = #{userId}")
    int deleteByUserId(Integer userId);

    @Select("SELECT role_id FROM user_role WHERE user_id = #{userId}")
    Integer[] findRoleIdsByUserId(Integer userId);
}
