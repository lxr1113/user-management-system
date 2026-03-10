package com.example.usermanagement.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductSku {
    private Integer id;
    private Integer productId;
    private String skuName;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private Integer stock;
    private String image;
    private Integer status;
    private LocalDateTime createTime;
}
