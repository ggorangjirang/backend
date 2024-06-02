package com.elice.ggorangjirang.users.controller;

import com.elice.ggorangjirang.global.login.service.LoginService;
import com.elice.ggorangjirang.users.dto.UserLoginDto;
import com.elice.ggorangjirang.users.dto.UserSignupDto;
import com.elice.ggorangjirang.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserSignupDto userSignupDto) throws Exception {
        userService.signUp(userSignupDto);

        return "회원가입이 완료되었습니다.";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserLoginDto userLoginDto) throws Exception {
        boolean isAuthenticated = loginService.login(userLoginDto);

        if (isAuthenticated) {
            return "로그인 성공"; // 예외처리 핸들러 작성 후 수정 예정
        } else {
            return "로그인 실패"; // 예외처리 핸들러 작성 후 수정 예정
        }
    }

}
