package com.elice.ggorangjirang.users.service;

import com.elice.ggorangjirang.users.dto.UsersSignupDto;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    public void signUp(UsersSignupDto usersSignupDto) throws Exception {
        if (usersRepository.findByEmail(usersSignupDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }


    }

}
