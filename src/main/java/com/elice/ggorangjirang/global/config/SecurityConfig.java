package com.elice.ggorangjirang.global.config;

import com.elice.ggorangjirang.global.login.filter.CustomUsernamePasswordAuthenticationFilter;
import com.elice.ggorangjirang.global.login.handler.LoginFailHandler;
import com.elice.ggorangjirang.global.login.handler.LoginSuccessHandler;
import com.elice.ggorangjirang.global.login.service.LoginService;
import com.elice.ggorangjirang.global.oauth2.handler.OAuth2LoginFailHandler;
import com.elice.ggorangjirang.global.oauth2.handler.OAuth2LoginSuccessHandler;
import com.elice.ggorangjirang.global.oauth2.service.CustomOAuth2UserService;
import com.elice.ggorangjirang.jwt.filter.JwtAuthenticationProcessingFilter;
import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Arrays;
import java.util.Collections;

// 인증은 CustomUsernamePasswordAuthenticationFilter에서 authenticate()로 인증된 사용자로 처리
// JwtAuthenticationProcessingFilter는 AccessToken, RefreshToken 재발급
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginService loginService;
    private final JwtService jwtService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper;

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    private final OAuth2LoginFailHandler oAuth2LoginFailHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors((cors) -> cors
                .configurationSource(request -> {

                    CorsConfiguration config = new CorsConfiguration();

                    config.setAllowedOrigins(Arrays.asList(
                        "http://localhost:3000",
                        "http://localhost:8080",
                        "https://ggorangjirang.duckdns.org"
                        ));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    return config;
                }));

            http
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/h2-console/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "api/**",
                        "/v3/api-docs/**",
                        "/api/v1/hello",
                        "/admin/**").permitAll()
                    .requestMatchers("/signup").permitAll()
                    .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            );

        http
            .formLogin(config -> config.disable())
            .httpBasic(config -> config.disable())
            .csrf(config -> config.disable())
            .headers(config -> config.disable())

            .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
            .oauth2Login(oauth2Login -> oauth2Login
                .successHandler(oAuth2LoginSuccessHandler)
                .failureHandler(oAuth2LoginFailHandler)
                .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(customOAuth2UserService))
            );

        http
            .addFilterAfter(customUsernamePasswordAuthenticationFilter(), LogoutFilter.class)
            .addFilterBefore(jwtAuthenticationProcessingFilter(), CustomUsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(loginService);
        return new ProviderManager(provider);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler(jwtService, userRepository);
    }

    @Bean
    public LoginFailHandler loginFailHandler() {
        return new LoginFailHandler();
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() {
        CustomUsernamePasswordAuthenticationFilter filter
            = new CustomUsernamePasswordAuthenticationFilter(objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(loginSuccessHandler());
        filter.setAuthenticationFailureHandler(loginFailHandler());
        return filter;
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
        JwtAuthenticationProcessingFilter filter = new JwtAuthenticationProcessingFilter(jwtService, userRepository);
        return filter;
    }

}
