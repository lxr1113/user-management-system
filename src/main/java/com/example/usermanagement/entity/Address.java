package com.example.usermanagement.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Address {
    private Integer id;
    private Integer userId;
    private String receiverName;
    private String phone;
    private String province;
    private String city;
    private String district;
    private String detailAddress;
    private Integer isDefault;
    private LocalDateTime createTime;
}
