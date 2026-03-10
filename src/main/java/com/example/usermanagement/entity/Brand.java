package com.example.usermanagement.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Brand {
    private Integer id;
    private String name;
    private String logo;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
