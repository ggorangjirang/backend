package com.elice.ggorangjirang.users.dto;

import com.elice.ggorangjirang.users.entity.Address;
import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name;
    private String phoneNumber;
    private String currentPassword; // 기존 비밀번호
    private String newPassword; // 새 배밀번호
    private String confirmPassword; // 비밀번호 확인
    private String zipcode;
    private String streetAddress;
    private String detailAddress;
}
