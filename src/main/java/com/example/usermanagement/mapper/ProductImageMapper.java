package com.example.usermanagement.mapper;

import com.example.usermanagement.entity.ProductImage;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductImageMapper {

    @Select("SELECT * FROM product_image WHERE product_id = #{productId} ORDER BY sort")
    List<ProductImage> findByProductId(Integer productId);

    @Insert("INSERT INTO product_image(product_id, url, sort) VALUES(#{productId}, #{url}, #{sort})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ProductImage image);

    @Delete("DELETE FROM product_image WHERE product_id = #{productId}")
    int deleteByProductId(Integer productId);
}
