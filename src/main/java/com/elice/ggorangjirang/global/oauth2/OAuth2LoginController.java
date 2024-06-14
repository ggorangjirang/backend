package com.elice.ggorangjirang.global.oauth2;

import com.elice.ggorangjirang.global.oauth2.service.CustomOAuth2UserService;
import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.users.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login/oauth2/code")
@RequiredArgsConstructor
public class OAuth2LoginController {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtService jwtService;

    @PostMapping("/kakao")
    public ResponseEntity<Map<String, String>> handleKakaoCode(@RequestParam("code") String authorizationCode) {
        try {
            // 인가 코드를 사용하여 카카오 서버에서 액세스 토큰 및 사용자 정보를 가져옴
            String accessToken = customOAuth2UserService.getAccessToken(authorizationCode);
            OAuthAttributes oAuthAttributes = customOAuth2UserService.getUserInfo(accessToken);

            // 사용자 정보를 데이터베이스에 저장하거나 업데이트
            Users user = customOAuth2UserService.addOrUpdateUser(oAuthAttributes);

            // JWT 토큰 생성
            String jwtAccessToken = jwtService.createAccessToken(user.getEmail());
            String jwtRefreshToken = jwtService.createRefreshToken();
            jwtService.updateRefreshToken(user.getEmail(), jwtRefreshToken);

            // JSON 응답 생성
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", jwtAccessToken);
            tokens.put("refreshToken", jwtRefreshToken);
            tokens.put("message", "로그인 성공");

            return ResponseEntity.ok(tokens);
        } catch (Exception e) {
            log.error("OAuth2 인가 코드 처리 중 예외 발생", e);
            return ResponseEntity.status(500).body(Map.of("message", "로그인 실패"));
        }
    }
}
