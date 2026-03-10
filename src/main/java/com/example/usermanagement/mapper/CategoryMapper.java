package com.example.usermanagement.mapper;

import com.example.usermanagement.entity.Category;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category ORDER BY sort")
    List<Category> findAll();

    @Select("SELECT * FROM category WHERE parent_id = #{parentId} ORDER BY sort")
    List<Category> findByParentId(@Param("parentId") Integer parentId);

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category findById(Integer id);

    @Insert("INSERT INTO category(name, parent_id, level, sort) VALUES(#{name}, #{parentId}, #{level}, #{sort})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Category category);

    @Update("UPDATE category SET name=#{name}, parent_id=#{parentId}, level=#{level}, sort=#{sort} WHERE id=#{id}")
    int update(Category category);

    @Delete("DELETE FROM category WHERE id = #{id}")
    int delete(Integer id);
}
