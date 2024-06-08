package com.elice.ggorangjirang.users.dto;

import com.elice.ggorangjirang.users.entity.Address;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Address address;
}
