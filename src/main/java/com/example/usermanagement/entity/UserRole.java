package com.example.usermanagement.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserRole {
    private Integer id;
    private Integer userId;
    private Integer roleId;
    private LocalDateTime createTime;
}
