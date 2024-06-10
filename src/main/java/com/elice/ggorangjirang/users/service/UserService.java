package com.elice.ggorangjirang.users.service;

import com.elice.ggorangjirang.carts.service.CartService;
import com.elice.ggorangjirang.users.dto.UserDto;
import com.elice.ggorangjirang.users.dto.UserSignupDto;
import com.elice.ggorangjirang.users.dto.UserUpdateRequest;
import com.elice.ggorangjirang.users.entity.Role;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;


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

        users.passwordEncode(passwordEncoder);
        userRepository.save(users);

        cartService.createCartForUser(users);
    }

    public Users findByUsername(String username) {
        return userRepository.findByEmailOrSocialId(username, username)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    }

    public Users findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found : " + id));
    }

    public UserDto getUserProfileByEmail(String email) {
        log.info("Searching for user with email: {}", email);
        Users users = userRepository.findByEmail(email)
            .orElseThrow(() -> {
                log.error("User not found with email: {}", email);
                return new RuntimeException("유저를 찾을 수 없습니다.");
            });

        log.info("User found: {}", users.getEmail());
        return converToDto(users);
    }

    public List<UserDto> getAllUsers() {
        List<Users> usersList = userRepository.findAll();
        return usersList.stream()
            .map(this::converToDto)
            .collect(Collectors.toList());
    }

    private UserDto converToDto(Users users) {
        UserDto userDto = new UserDto();

        userDto.setId(users.getId());
        userDto.setName(users.getName());
        userDto.setEmail(users.getEmail());
        userDto.setPhoneNumber(users.getPhoneNumber());
        userDto.setAddress(users.getAddress());

        return userDto;
    }

    public Users getUsersInfoByEmail(String email) {
        log.info("Searching for user with email: {}", email);
        Users users = userRepository.findByEmail(email)
            .orElseThrow(() -> {
                log.error("User not found with email: {}", email);
                return new RuntimeException("유저를 찾을 수 없습니다.");
            });

        log.info("User found: {}", users.getEmail());
        return users;
    }

    public void updateUserProfile(String email, UserUpdateRequest updateRequest) {
        Users users = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        if (updateRequest.getName() != null) {
            users.setName(updateRequest.getName());
        }
        if (updateRequest.getPhoneNumber() != null) {
            users.setPhoneNumber(updateRequest.getPhoneNumber());
        }
        if (updateRequest.getCurrentPassword() != null && updateRequest.getNewPassword() != null
            && updateRequest.getConfirmPassword() != null) {
            if (!passwordEncoder.matches(updateRequest.getCurrentPassword(), users.getPassword())) {
                throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
            }
            if (!updateRequest.getNewPassword().equals(updateRequest.getConfirmPassword())) {
                throw new IllegalArgumentException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            }
            users.setPassword(passwordEncoder.encode(updateRequest.getNewPassword()));
        }
        if (updateRequest.getAddress() != null) {
            users.setAddress(updateRequest.getAddress());
        }

        userRepository.save(users);
    }

    public void deleteUser(String email) {
        Users users = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        users.delete();
        userRepository.save(users);
    }

}
