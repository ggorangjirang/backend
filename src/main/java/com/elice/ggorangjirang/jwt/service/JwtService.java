package com.elice.ggorangjirang.jwt.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.elice.ggorangjirang.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
@Slf4j
@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    @Value("${jwt.access.header}")
    private String accessHeader;

    @Value("${jwt.refresh.header}")
    private String refreshHeader;

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private static final String EMAIL_CLAIM = "email"; // 클레임으로 email 사용
    private static final String BEARER = "Bearer";

    private final UserRepository userRepository;

    // AccessToken 생성 메소드
    public String createAccessToken(String email) {
        Date now = new Date();

        return JWT.create()
            .withSubject(ACCESS_TOKEN_SUBJECT)
            .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
            .withClaim(EMAIL_CLAIM, email)
            .sign(Algorithm.HMAC512(secretKey));
    }

    // RefreshToken 생성 메소드
    public String createRefreshToken() {
        Date now = new Date();

        return JWT.create()
            .withSubject(REFRESH_TOKEN_SUBJECT)
            .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
            .sign(Algorithm.HMAC512(secretKey));
    }

    // AccessToken 헤더에 실어서 보내는 메소드
    public void sendAccessToken(HttpServletResponse response, String accessToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        response.setHeader(accessHeader, accessToken);
        log.info("재발급된 Access Token : {}", accessToken);
    }

    // AccessToken + RefreshToken 응답 바디에 실어서 보내는 메소드
    public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken)
        throws IOException {
        if (!response.isCommitted()) {
            response.setStatus(HttpServletResponse.SC_OK);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", "Bearer " + accessToken);
            if (refreshToken != null) {
                tokens.put("refreshToken", "Bearer " + refreshToken);
            }
            tokens.put("message", "로그인 성공"); // 로그인 성공 시 메시지
            response.setContentType("application/json");
            
            PrintWriter writer = response.getWriter();
            writer.write(new ObjectMapper().writeValueAsString(tokens));
            writer.flush();
            writer.close();

            log.info("Access Token, Refresh Token 응답 바디 설정 완료");
        } else {
            log.warn("응답이 이미 커밋되었습니다.");
        }
    }

    // AccessToken 헤더 설정
    public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(accessHeader, accessToken);
    }

    // RefreshToken 헤더 설정
    public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(refreshHeader, refreshToken);
    }

    // 헤더에서 AccessToken 추출
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
            .filter(accessToken -> accessToken.startsWith(BEARER))
            .map(accessToken -> accessToken.replace(BEARER, ""));
    }

    // 헤더에서 RefreshToken 추출
    public Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(refreshHeader))
            .filter(refreshToken -> refreshToken.startsWith(BEARER))
            .map(refreshToken -> refreshToken.replace(BEARER, ""));
    }

    // AccessToken에서 email 추출
    public Optional<String> extractEmail(String accessToken) {
        try {
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                    .build()
                    .verify(accessToken)
                    .getClaim(EMAIL_CLAIM)
                .asString());
        } catch (Exception e) {
            log.error("액세스 토큰이 유효하지 않습니다.");
            return Optional.empty();
        }
    }

    // RefreshToken DB에 저장
    public void updateRefreshToken(String email, String refreshToken) {
        userRepository.findByEmail(email)
            .ifPresentOrElse(
                    users -> users.updateRefreshToken(refreshToken),
                    () -> new Exception("일치하는 회원이 없습니다.")
            );
    }

    // 토큰이 유효한 토큰인지 체크
    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
            return true;
        } catch (Exception e) {
            log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
            return false;
        }
    }

}
