package com.example.usermanagement.mapper;

import com.example.usermanagement.entity.Role;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface RoleMapper {

    @Select("SELECT * FROM role")
    List<Role> findAll();

    @Select("SELECT * FROM role WHERE id = #{id}")
    Role findById(Integer id);

    @Select("SELECT r.* FROM role r " +
            "INNER JOIN user_role ur ON r.id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<Role> findByUserId(Integer userId);

    @Insert("INSERT INTO role(name, code, description, status) VALUES(#{name}, #{code}, #{description}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Role role);

    @Update("UPDATE role SET name=#{name}, code=#{code}, description=#{description}, status=#{status} WHERE id=#{id}")
    int update(Role role);

    @Delete("DELETE FROM role WHERE id = #{id}")
    int delete(Integer id);
}
