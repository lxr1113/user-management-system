package com.example.usermanagement.dto;

import lombok.Data;

@Data
public class PageParam {
    private Long current = 1L;
    private Long size = 10L;
    private String keyword;
    private Integer categoryId;
    private Integer brandId;
    private Integer status;
}
