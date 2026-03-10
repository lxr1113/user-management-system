package com.example.usermanagement.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Product {
    private Integer id;
    private String name;
    private Integer categoryId;
    private Integer brandId;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private Integer stock;
    private Integer sales;
    private String description;
    private String detail;
    private String image;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    private Category category;
    private Brand brand;
    private List<ProductImage> images;
    private List<ProductSku> skus;
}
