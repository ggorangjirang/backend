package com.elice.ggorangjirang.users.controller;

import com.elice.ggorangjirang.global.login.service.LoginService;
import com.elice.ggorangjirang.users.dto.UserLoginDto;
import com.elice.ggorangjirang.users.dto.UserSignupDto;
import com.elice.ggorangjirang.users.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) throws Exception {
        try {
            loginService.login(userLoginDto, response);
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("status", response.getStatus());
            responseBody.put("message", "로그인 성공");

            return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody);
        } catch (Exception e) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("status", HttpStatus.UNAUTHORIZED.value());
            responseBody.put("message", "로그인 실패: " + e.getMessage());

            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("로그인 실패: " + e.getMessage());
        }
    }
}
