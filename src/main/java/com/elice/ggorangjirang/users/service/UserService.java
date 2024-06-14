package com.elice.ggorangjirang.users.service;

import com.elice.ggorangjirang.carts.service.CartService;
import com.elice.ggorangjirang.global.email.service.EmailService;
import com.elice.ggorangjirang.users.dto.UserDto;
import com.elice.ggorangjirang.users.dto.UserSignupDto;
import com.elice.ggorangjirang.users.dto.UserUpdateRequest;
import com.elice.ggorangjirang.users.entity.Address;
import com.elice.ggorangjirang.users.entity.Role;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.entity.VerificationToken;
import com.elice.ggorangjirang.users.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final EmailService emailService;

    public void signUp(UserSignupDto userSignupDto) throws Exception {
        if (userRepository.findByEmail(userSignupDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        Users users = Users.builder()
            .email(userSignupDto.getEmail())
            .password(userSignupDto.getPassword())
            .name(userSignupDto.getName())
            .role(Role.USER)
            .enabled(false)
            .build();

        users.passwordEncode(passwordEncoder);
        userRepository.save(users);

        cartService.createCartForUser(users);
        sendVerficationEmail(users);
    }

    public boolean isEmailDuplicate(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private void sendVerficationEmail(Users users) {
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);

        VerificationToken verificationToken = new VerificationToken(token, expiryDate);
        users.setVerificationToken(verificationToken);
        userRepository.save(users);

        String recipientAddress = users.getEmail();
        String subject = "회원가입 이메일 인증";
        String confirmationUrl = "http://localhost:8080/api/users/confirm?token=" + token;
        String message = "<p>회원가입을 완료하려면 아래 버튼을 클릭하세요.</p>"
            + "<a href=\"" + confirmationUrl + "\" style=\"display: inline-block; padding: 10px 20px; " +
            "font-size: 16px; color: #fff; background-color: #007bff; text-decoration: none; " +
            "border-radius: 5px;\">이메일 인증</a>";

        try {
            emailService.sendHtmlMessage(recipientAddress, subject, message);
        } catch (MessagingException e) {
            throw new RuntimeException("이메일 전송에 실패했습니다.", e);
        }
    }

    public void confirmUser(String token) {
        Users users = userRepository.findByVerificationToken_Token(token)
            .orElseThrow(() -> new RuntimeException("유효하지 않은 토큰입니다."));

        if (users.getVerificationToken().getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("토큰이 만료되었습니다.");
        }

        users.setEnabled(true); // 계정 활성화
        users.setVerificationToken(null); // 토큰 제거
        userRepository.save(users);
    }

    public Users findByUsername(String username) {
        return userRepository.findByEmailOrSocialId(username, username)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    }

    public Users findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found : " + id));
    }

    public UserDto getUserMypageByEmail(String email) {
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

    public UserDto converToDto(Users users) {
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

    public void updateUserMypage(String email, UserUpdateRequest updateRequest) {
        Users users = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // 현재 비밀번호가 입력되지 않은 경우 예외 발생
        if (updateRequest.getCurrentPassword() == null || updateRequest.getCurrentPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("현재 비밀번호를 입력해야 합니다.");
        }

        // 현재 비밀번호가 일치하는지 확인
        if (!passwordEncoder.matches(updateRequest.getCurrentPassword(), users.getPassword())) {
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
        }

        if (updateRequest.getName() != null) {
            users.setName(updateRequest.getName());
        }
        if (updateRequest.getPhoneNumber() != null) {
            users.setPhoneNumber(updateRequest.getPhoneNumber());
        }
        if (updateRequest.getZipcode() != null || updateRequest.getStreetAddress() != null ||
            updateRequest.getDetailAddress() != null) {
            Address address = new Address(updateRequest.getZipcode(), updateRequest.getStreetAddress(),
                updateRequest.getDetailAddress());
            users.setAddress(address);
        }

        // 새 비밀번호와 비밀번호 확인이 모두 입력된 경우(비밀번호 변경)
        if (updateRequest.getNewPassword() != null || updateRequest.getConfirmPassword() != null) {
            if (updateRequest.getNewPassword() == null || updateRequest.getConfirmPassword() == null) {
                throw new IllegalArgumentException("새 비밀번호와 비밀번호 확인을 모두 입력해야 합니다.");
            }
            if (!updateRequest.getNewPassword().equals(updateRequest.getConfirmPassword())) {
                throw new IllegalArgumentException("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
            }
            if (updateRequest.getNewPassword().trim().isEmpty()) {
                throw new IllegalArgumentException("새 비밀번호는 공백일 수 없습니다.");
            }
            users.setPassword(passwordEncoder.encode(updateRequest.getNewPassword()));
        }

        userRepository.save(users);
    }

    public void deleteUser(String email) {
        Users users = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        users.delete();
        userRepository.save(users);
    }

    public Long getUserIdByEmail(String email) {
        Users user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        return user.getId();
    }

}
