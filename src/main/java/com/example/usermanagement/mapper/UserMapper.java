package com.example.usermanagement.mapper;

import com.example.usermanagement.entity.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE del_flag = 0")
    List<User> findAll();

    @Select("SELECT * FROM user WHERE id = #{id} AND del_flag = 0")
    User findById(Integer id);

    @Select("SELECT * FROM user WHERE username = #{username} AND del_flag = 0")
    User findByUsername(String username);

    @Select("SELECT * FROM user WHERE del_flag = 0 " +
            "AND (#{keyword} IS NULL OR username LIKE CONCAT('%', #{keyword}, '%') OR email LIKE CONCAT('%', #{keyword}, '%')) " +
            "LIMIT #{offset}, #{size}")
    List<User> findByPage(@Param("keyword") String keyword, @Param("offset") Long offset, @Param("size") Long size);

    @Select("SELECT COUNT(*) FROM user WHERE del_flag = 0 " +
            "AND (#{keyword} IS NULL OR username LIKE CONCAT('%', #{keyword}, '%') OR email LIKE CONCAT('%', #{keyword}, '%'))")
    Long countByPage(@Param("keyword") String keyword);

    @Insert("INSERT INTO user(username, password, email, age, status, create_by, create_time) " +
            "VALUES(#{username}, #{password}, #{email}, #{age}, #{status}, #{createBy}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Update("UPDATE user SET username=#{username}, password=#{password}, email=#{email}, age=#{age}, " +
            "status=#{status}, update_by=#{updateBy}, update_time=NOW() WHERE id=#{id}")
    int update(User user);

    @Update("UPDATE user SET del_flag = 1 WHERE id = #{id}")
    int delete(Integer id);
}