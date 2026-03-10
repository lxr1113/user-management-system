package com.example.usermanagement.mapper;

import com.example.usermanagement.entity.ProductSku;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductSkuMapper {

    @Select("SELECT * FROM product_sku WHERE product_id = #{productId}")
    List<ProductSku> findByProductId(Integer productId);

    @Select("SELECT * FROM product_sku WHERE id = #{id}")
    ProductSku findById(Integer id);

    @Insert("INSERT INTO product_sku(product_id, sku_name, price, original_price, stock, image, status) " +
            "VALUES(#{productId}, #{skuName}, #{price}, #{originalPrice}, #{stock}, #{image}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(ProductSku sku);

    @Update("UPDATE product_sku SET sku_name=#{skuName}, price=#{price}, original_price=#{originalPrice}, " +
            "stock=#{stock}, image=#{image}, status=#{status} WHERE id=#{id}")
    int update(ProductSku sku);

    @Delete("DELETE FROM product_sku WHERE product_id = #{productId}")
    int deleteByProductId(Integer productId);
}
