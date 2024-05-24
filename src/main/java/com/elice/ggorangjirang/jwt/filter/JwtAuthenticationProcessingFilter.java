package com.elice.ggorangjirang.jwt.filter;

import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private static final String NO_CHECK_URL = "/login";

    private final JwtService jwtService;
    private final UserRepository userRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        // "/login" 요청이 들어온 경우 다음 필터 진행
        if (request.getRequestURI().equals(NO_CHECK_URL)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 사용자 요청 헤더에서 Refresh Token 추출
        String refreshToken = jwtService.extractRefreshToken(request)
            .filter(jwtService::isTokenValid)
            .orElse(null);

        // 요청 헤더에 Refresh Token이 있는 경우
        if (refreshToken != null) {
            checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
            return;
        }

        if (refreshToken == null) {
            checkAccessTokenAndAuthentication(request, response, filterChain);
        }
    }

    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        userRepository.findByRefreshToken(refreshToken)
            .ifPresent(users -> {
                String reIssuedRefreshedToken = reIssueRefreshToken(users);
                jwtService.sendAccessAndRefreshToken(response, jwtService.createAccessToken(users.getEmail()),
                    reIssuedRefreshedToken);
            });
    }

    public void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                 FilterChain filterChain) throws ServletException, IOException {
        log.info("checkAccessTokenAndAuthentication() 호출");
        jwtService.extractAccessToken(request)
            .filter(jwtService::isTokenValid)
            .ifPresent(accessToken -> jwtService.extractEmail(accessToken)
                .ifPresent(email -> userRepository.findByEmail(email)
                    .ifPresent(this::saveAuthentication)));
        filterChain.doFilter(request, response);
    }

    private String reIssueRefreshToken(Users users) {
        String reIssuedRefreshToken = jwtService.createRefreshToken();
        users.updateRefreshToken(reIssuedRefreshToken);
        userRepository.saveAndFlush(users);
        return reIssuedRefreshToken;
    }

    // 인증 허가 메소드
    public void saveAuthentication(User myUser) {
        String password = myUser.getPassword();

        if(password == null) {
            // 소셜 로그인시
        }


    }
}
