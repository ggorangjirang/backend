package com.elice.ggorangjirang.users.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserAdminDto {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private String role;
}
