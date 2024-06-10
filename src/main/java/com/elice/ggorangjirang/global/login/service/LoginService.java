package com.elice.ggorangjirang.global.login.service;

import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.jwt.util.CustomUserDetails;
import com.elice.ggorangjirang.users.dto.UserLoginDto;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));

        if (users.isDeleted()) {
            throw new UsernameNotFoundException("탈퇴한 사용자입니다.");
        }

        return new CustomUserDetails(users);
    }

    public void login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {

        log.info("loginService, login UserLoginDto: {}", userLoginDto);
        var users = userRepository.findByEmail(userLoginDto.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));

        if (users.isDeleted()) {
            throw new UsernameNotFoundException("탈퇴한 사용자입니다.");
        }

        if (passwordEncoder.matches(userLoginDto.getPassword(), users.getPassword())) {
            String accessToken = jwtService.createAccessToken(users.getEmail());
            String refreshToken = jwtService.createRefreshToken();

            users.updateRefreshToken(refreshToken);
            userRepository.saveAndFlush(users);

            try {
                // JSON 응답 바디 설정
                jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
            } catch (IOException e) {
                log.error("토큰 전송에 실패했습니다.", e);
            }

            // 응답 헤더를 AccessToken 과 RefreshToken 설정
            response.setHeader(jwtService.getAccessHeader(), JwtService.BEARER + accessToken);
            response.setHeader(jwtService.getRefreshHeader(), JwtService.BEARER + refreshToken);
        } else {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
    }
}

