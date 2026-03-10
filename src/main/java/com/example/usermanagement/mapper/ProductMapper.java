package com.example.usermanagement.mapper;

import com.example.usermanagement.entity.Product;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM product WHERE status = 1 ORDER BY sales DESC")
    List<Product> findAll();

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findById(Integer id);

    @Select("SELECT * FROM product WHERE status = 1 " +
            "AND (#{keyword} IS NULL OR name LIKE CONCAT('%', #{keyword}, '%')) " +
            "AND (#{categoryId} IS NULL OR category_id = #{categoryId}) " +
            "AND (#{brandId} IS NULL OR brand_id = #{brandId}) " +
            "ORDER BY sales DESC LIMIT #{offset}, #{size}")
    List<Product> findByPage(@Param("keyword") String keyword, 
                             @Param("categoryId") Integer categoryId,
                             @Param("brandId") Integer brandId,
                             @Param("offset") Long offset, 
                             @Param("size") Long size);

    @Select("SELECT COUNT(*) FROM product WHERE status = 1 " +
            "AND (#{keyword} IS NULL OR name LIKE CONCAT('%', #{keyword}, '%')) " +
            "AND (#{categoryId} IS NULL OR category_id = #{categoryId}) " +
            "AND (#{brandId} IS NULL OR brand_id = #{brandId})")
    Long countByPage(@Param("keyword") String keyword, 
                     @Param("categoryId") Integer categoryId,
                     @Param("brandId") Integer brandId);

    @Insert("INSERT INTO product(name, category_id, brand_id, price, original_price, stock, sales, description, detail, image, status) " +
            "VALUES(#{name}, #{categoryId}, #{brandId}, #{price}, #{originalPrice}, #{stock}, #{sales}, #{description}, #{detail}, #{image}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Product product);

    @Update("UPDATE product SET name=#{name}, category_id=#{categoryId}, brand_id=#{brandId}, " +
            "price=#{price}, original_price=#{originalPrice}, stock=#{stock}, sales=#{sales}, " +
            "description=#{description}, detail=#{detail}, image=#{image}, status=#{status} WHERE id=#{id}")
    int update(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    int delete(Integer id);
}
