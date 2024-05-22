package com.elice.ggorangjirang.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UsersSignupDto {
    private String email;
    private String password;
    private String name;
}
