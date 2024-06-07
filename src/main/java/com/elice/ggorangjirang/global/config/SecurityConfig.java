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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;
import static org.springframework.security.config.Customizer.withDefaults;

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
            .cors(withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/h2-console/**",
                                "/swagger-ui/**",
                                "/swagger-resources/**",
                                "/api/**",
                                "/api/login/oauth2/**",
                                "/v3/api-docs/**",
                                "/admin/**",
                                "/js/**",
                                "/css/**",
                                "/actuator/**")
                    .permitAll()
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

    // cors() 설정을 별도의 bean으로 관리
    @Bean
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "http://localhost:3000",
                "https://ggorangjirang.duckdns.org"
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(List.of("x-auth-token"));
        configuration.setAllowCredentials(true); // 쿠키와 인증 정보를 포함한 요청이 가능하도록 설정

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
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

