package com.elice.ggorangjirang.users.controller;

import com.elice.ggorangjirang.global.login.service.LoginService;
import com.elice.ggorangjirang.users.dto.UserLoginDto;
import com.elice.ggorangjirang.users.dto.UserSignupDto;
import com.elice.ggorangjirang.users.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/signup")
    public String signUp(@RequestBody UserSignupDto userSignupDto) throws Exception {
        userService.signUp(userSignupDto);

        logger.info("회원가입이 완료되었습니다. 사용자 이메일: {}", userSignupDto.getEmail());

        return "회원가입이 완료되었습니다.";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) throws Exception {
        try {
            loginService.login(userLoginDto, response);
            logger.info("로그인 성공: 사용자 이메일 {}", userLoginDto.getEmail());
            return ResponseEntity.ok().body("로그인 성공");
        } catch (Exception e) {
            logger.error("로그인 실패: 사용자 이메일 {}. 에러 메시지: {}", userLoginDto.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패: " + e.getMessage());
        }

    }

}
