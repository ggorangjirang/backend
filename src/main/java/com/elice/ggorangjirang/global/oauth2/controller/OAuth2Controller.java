package com.elice.ggorangjirang.global.oauth2.controller;

import com.elice.ggorangjirang.global.oauth2.CustomOAuth2User;
import com.elice.ggorangjirang.global.oauth2.OAuthAttributes;
import com.elice.ggorangjirang.global.oauth2.service.CustomOAuth2UserService;
import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2")
public class OAuth2Controller {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CustomOAuth2UserService customOAuth2UserService;

    @GetMapping("/loginSuccess")
    public ResponseEntity<Map<String, String>> loginSuccess(OAuth2AuthenticationToken authentication) {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
        String refreshToken = jwtService.createRefreshToken();

        Users users = customOAuth2UserService.getUser(OAuthAttributes.ofKakao(oAuth2User.getNameAttributeKey(),
            oAuth2User.getAttributes()));
        users.updateRefreshToken(refreshToken);
        userRepository.save(users);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return ResponseEntity.ok(tokens);
    }
}
