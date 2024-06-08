package com.elice.ggorangjirang.jwt.filter;

import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.jwt.util.PasswordUtil;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private static final String NO_CHECK_URL = "/login"; // "/login"으로 들어오는 요청은 Filter 작동x
    private static final String NO_CHECK_ACTUATOR = "/actuator"; // "/actuator"으로 들어오는 요청은 Filter 작동x
    private static final String NO_CHECK_OAUTH2 = "/oauth2/"; // "/oauth2/"으로 들어오는 요청은 Filter 작동x

    private final JwtService jwtService;
    private final UserRepository userRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // "/login" 요청이 들어온 경우 다음 필터 진행
        if (requestURI.equals(NO_CHECK_URL) || requestURI.startsWith(NO_CHECK_ACTUATOR)
            || requestURI.startsWith(NO_CHECK_OAUTH2)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 커밋되지 않은 경우에만 checkAccessTokenAndAuthentication 메소드 호출
        if (!response.isCommitted()) {
            checkAccessTokenAndAuthentication(request, response, filterChain);

            // 사용자 요청 헤더에서 Refresh Token 추출
            String refreshToken = jwtService.extractRefreshToken(request)
                .filter(jwtService::isTokenValid)
                .orElse(null);

            // 요청 헤더에 Refresh Token이 있는 경우
            if (refreshToken != null) {
                checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
                return;
            }
        } else {
            log.warn("응답이 이미 커밋되었습니다.");
        }

        filterChain.doFilter(request, response);
    }

    // Refresh Token으로 유저 정보 찾기 + 토큰 재발급 메소드
    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
        userRepository.findByRefreshToken(refreshToken)
            .ifPresent(users -> {
                String reIssuedRefreshedToken = reIssueRefreshToken(users);

                try {
                    jwtService.sendAccessAndRefreshToken(response, jwtService.createAccessToken(users.getEmail()),
                        reIssuedRefreshedToken);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                } catch (IOException e) {
                    log.error("토큰 전송에 실패했습니다." , e);
                }
            });
    }

    // Access Token 체크 + 인증 처리 메소드
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

    // Refresh Token 재발급 + DB에 업데이트 메소드
    private String reIssueRefreshToken(Users users) {
        String reIssuedRefreshToken = jwtService.createRefreshToken();
        users.updateRefreshToken(reIssuedRefreshToken);
        userRepository.saveAndFlush(users);
        return reIssuedRefreshToken;
    }

    // 인증 허가 메소드
    public void saveAuthentication(Users myUser) {
        String password = myUser.getPassword();

        // OAuth2 로그인시 유저의 비밀번호 임의로 설정하여 OAuth2 유저도 인증되도록 설정
        if(password == null) {
            password = PasswordUtil.generateRandomPassword();
        }

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
            .username(myUser.getEmail())
            .password(password)
            .roles(myUser.getRole().name())
            .build();

        Authentication authentication =
            new UsernamePasswordAuthenticationToken(userDetails, null,
                authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

