package com.example.usermanagement.entity;

import lombok.Data;

@Data
public class ProductImage {
    private Integer id;
    private Integer productId;
    private String url;
    private Integer sort;
}
