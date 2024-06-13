package com.elice.ggorangjirang.global.oauth2.handler;

import com.elice.ggorangjirang.global.oauth2.CustomOAuth2User;
import com.elice.ggorangjirang.global.oauth2.OAuthAttributes;
import com.elice.ggorangjirang.global.oauth2.service.CustomOAuth2UserService;
import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.users.entity.Role;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.access.expiration}")
    private Long accessTokenExpirationPeriod;

    @Value("${jwt.refresh.expiration}")
    private Long refreshTokenExpirationPeriod;

    public static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    public static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
    public static final String ACCESS_TOKEN_EXPIRATION = "AccessTokenExpired";
    public static final String REFRESH_TOKEN_EXPIRATION = "RefreshTokenExpired";

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login 성공!");

        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            log.debug("Authenticated user: {}", oAuth2User);

            // User의 Role이 GUEST인 경우 처음 요청한 회원
            if (oAuth2User.getRole() == Role.GUEST) {
                log.info("ROLE.GUEST if문 진입");
                OAuthAttributes oAuthAttributes =
                    OAuthAttributes.ofKakao(oAuth2User.getNameAttributeKey(), oAuth2User.getAttributes());
                Users newUser = customOAuth2UserService.addUser(oAuthAttributes);
                log.info("유저 DB: {}", newUser);

                handleSuccessResponse(response, newUser.getEmail());
            } else if (oAuth2User.getRole() == Role.USER) {
                log.info("ROLE.USER if문 진입");
                handleSuccessResponse(response, oAuth2User.getEmail());
            }
        } catch (Exception e) {
            log.error("OAuth2 Login 성공 후 예외 발생", e);
            throw new ServletException("OAuth2 Login 성공 후 예외 발생", e);
        }
    }

    private void handleSuccessResponse(HttpServletResponse response, String email) throws IOException {
        String accessToken = jwtService.createAccessToken(email);
        String refreshToken = jwtService.createRefreshToken();

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put(ACCESS_TOKEN_SUBJECT, accessToken);
        responseBody.put(REFRESH_TOKEN_SUBJECT, refreshToken);
        responseBody.put(ACCESS_TOKEN_EXPIRATION, String.valueOf(System.currentTimeMillis() + accessTokenExpirationPeriod));
        responseBody.put(REFRESH_TOKEN_EXPIRATION, String.valueOf(System.currentTimeMillis() + refreshTokenExpirationPeriod));

        String jsonResponse = objectMapper.writeValueAsString(responseBody);
        log.info("JSON Response: {}", jsonResponse);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();
        writer.write("<html><body>");
        writer.write("<script>");
        writer.write("const response = " + jsonResponse + ";");
        writer.write("if (window.opener) {");
        writer.write("  console.log('Posting message to opener and closing window');");
        writer.write("  window.opener.postMessage(response, '*');");
        writer.write("  window.close();");
        writer.write("} else {");
        writer.write("  console.error('No window.opener available');");
        writer.write("}");
        writer.write("</script>");
        writer.write("</body></html>");
        writer.flush();

        log.info("Jwt AccessToken 및 RefreshToken 생성 및 설정 완료");
    }
}
