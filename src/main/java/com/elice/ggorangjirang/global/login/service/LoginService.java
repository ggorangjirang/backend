package com.elice.ggorangjirang.global.login.service;

import com.elice.ggorangjirang.jwt.util.CustomUserDetails;
import com.elice.ggorangjirang.users.dto.UserLoginDto;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));

        return new CustomUserDetails(users);
    }

    public boolean login(UserLoginDto userLoginDto) {
        var user = userRepository.findByEmail(userLoginDto.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다."));
        return passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword());
    }

}

