package com.example.usermanagement.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Category {
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer level;
    private Integer sort;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
