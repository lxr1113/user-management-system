package com.example.usermanagement.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RolePermission {
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
    private LocalDateTime createTime;
}
