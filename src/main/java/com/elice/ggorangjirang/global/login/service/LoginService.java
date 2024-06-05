package com.elice.ggorangjirang.global.login.service;

import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.jwt.util.CustomUserDetails;
import com.elice.ggorangjirang.users.dto.UserLoginDto;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));

        return new CustomUserDetails(users);
    }

    public String login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
        var user = userRepository.findByEmail(userLoginDto.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));

        if (passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            String accessToken = jwtService.createAccessToken(user.getEmail());
            String refreshToken = jwtService.createRefreshToken();

            user.updateRefreshToken(refreshToken);
            userRepository.saveAndFlush(user);
            jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
            return accessToken;
        } else {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
    }

}

