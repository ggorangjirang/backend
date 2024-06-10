package com.elice.ggorangjirang.global.oauth2.handler;

import com.elice.ggorangjirang.global.oauth2.CustomOAuth2User;
import com.elice.ggorangjirang.global.oauth2.OAuthAttributes;
import com.elice.ggorangjirang.global.oauth2.service.CustomOAuth2UserService;
import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.users.entity.Role;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");

        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            // User의 Role이 GUEST인 경우 처음 요청한 회원
            if (oAuth2User.getRole() == Role.GUEST) {
                log.info("ROLE.GUEST if문 진입");
                OAuthAttributes oAuthAttributes =
                    OAuthAttributes.ofKakao(oAuth2User.getNameAttributeKey(), oAuth2User.getAttributes());
                Users newUser = customOAuth2UserService.addUser(oAuthAttributes);
                log.info("유저 DB: ", newUser);
                newUser.setRole(Role.USER); // Role을 USER로 변경
                String accessToken = jwtService.createAccessToken(newUser.getEmail());
                String refreshToken = jwtService.createRefreshToken();
                newUser.updateRefreshToken(refreshToken);
                userRepository.save(newUser);

                jwtService.setAccessTokenHeader(response, accessToken);
                jwtService.setRefreshTokenHeader(response, refreshToken);
                jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
                log.info("Jwt AccessToken 및 RefreshToken 생성 및 설정 완료");
            } else if (oAuth2User.getRole() == Role.USER) {
                loginSuccess(response, oAuth2User); // 로그인에 성공한 경우 access, refresh 토큰 생성
                response.sendRedirect("/main");
            }
        } catch (Exception e) {
            log.error("OAuth2 Login 성공 후 예외 발생", e);
            throw new ServletException("OAuth2 Login 성공 후 예외 발생", e);
        }
    }

    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        String accessToken = jwtService.createAccessToken(oAuth2User.getEmail());
        String refreshToken = jwtService.createRefreshToken();

        jwtService.setAccessTokenHeader(response, accessToken);
        jwtService.setRefreshTokenHeader(response, refreshToken);

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(oAuth2User.getEmail(), refreshToken);
    }
}
