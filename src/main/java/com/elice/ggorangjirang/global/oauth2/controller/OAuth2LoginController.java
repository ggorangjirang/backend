package com.elice.ggorangjirang.global.oauth2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OAuth2LoginController {
    @GetMapping("/login/oauth2/code/kakao")
    public ResponseEntity<?> handleKakaoLogin(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok("OAuth2 로그인 성공!"); // 테스트 성공, 수정 예정
    }

}
