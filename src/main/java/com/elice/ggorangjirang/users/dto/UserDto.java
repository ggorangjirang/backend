package com.elice.ggorangjirang.users.dto;

import com.elice.ggorangjirang.users.entity.Address;
import lombok.Data;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String address;
}

