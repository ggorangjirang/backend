package com.elice.ggorangjirang.users.service;

import com.elice.ggorangjirang.users.dto.UserSignupDto;
import com.elice.ggorangjirang.users.entity.Role;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //private final PasswordEncoder passwordEncoder;


    public void signUp(UserSignupDto userSignupDto) throws Exception {
        if (userRepository.findByEmail(userSignupDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        Users users = Users.builder()
            .email(userSignupDto.getEmail())
            .password(userSignupDto.getPassword())
            .name(userSignupDto.getName())
            .role(Role.USER)
            .build();

        //users.passwordEncode(passwordEncoder);
        userRepository.save(users);
    }

}
