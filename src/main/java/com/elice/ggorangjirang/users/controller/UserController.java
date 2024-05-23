package com.elice.ggorangjirang.users.controller;

import com.elice.ggorangjirang.users.dto.UserSignupDto;
import com.elice.ggorangjirang.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserSignupDto userSignupDto) throws Exception {
        userService.signUp(userSignupDto);

        return "회원가입이 완료되었습니다.";
    }

}
