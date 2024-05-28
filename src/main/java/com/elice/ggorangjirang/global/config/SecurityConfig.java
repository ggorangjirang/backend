package com.elice.ggorangjirang.global.config;

import com.elice.ggorangjirang.global.login.filter.CustomUsernamePasswordAuthenticationFilter;
import com.elice.ggorangjirang.global.login.handler.LoginFailHandler;
import com.elice.ggorangjirang.global.login.handler.LoginSuccessHandler;
import com.elice.ggorangjirang.global.login.service.LoginService;
import com.elice.ggorangjirang.jwt.filter.JwtAuthenticationProcessingFilter;
import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoginService loginService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .formLogin().disable()
            .httpBasic().disable()
            .csrf().disable()
            .headers().disable()

            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()

            .authorizeRequests(authorize -> authorize
                    .requestMatchers("/h2-console/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated()
            );









            // OAuth2.0 구현 후 추가 예정

            return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(loginService);
        return new ProviderManager(provider);
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return null; // 구현 예정
    }

    @Bean
    public LoginFailHandler loginFailHandler() {
        return null; // 구현 예정
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() {
        CustomUsernamePasswordAuthenticationFilter filter
            = new CustomUsernamePasswordAuthenticationFilter(objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
        JwtAuthenticationProcessingFilter filter = new JwtAuthenticationProcessingFilter(jwtService, userRepository);
        return filter;
    }

}
