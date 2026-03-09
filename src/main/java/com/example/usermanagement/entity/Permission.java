package com.example.usermanagement.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Permission {
    private Integer id;
    private String name;
    private String code;
    private String type;
    private String url;
    private Integer parentId;
    private Integer sort;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
