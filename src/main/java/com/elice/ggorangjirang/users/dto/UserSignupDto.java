package com.elice.ggorangjirang.users.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignupDto {
    private String email;
    private String password;
    private String name;
}
