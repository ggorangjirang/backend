package com.elice.ggorangjirang.users.controller;

import com.elice.ggorangjirang.global.login.service.LoginService;
import com.elice.ggorangjirang.users.dto.UserDto;
import com.elice.ggorangjirang.users.dto.UserLoginDto;
import com.elice.ggorangjirang.users.dto.UserSignupDto;
import com.elice.ggorangjirang.users.dto.UserUpdateRequest;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserSignupDto userSignupDto) throws Exception {
        userService.signUp(userSignupDto);

        return "회원가입이 완료되었습니다.";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) throws Exception {
        try {
            loginService.login(userLoginDto, response);
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("status", response.getStatus());
            responseBody.put("message", "로그인 성공");

            return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody);
        } catch (Exception e) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("status", HttpStatus.UNAUTHORIZED.value());
            responseBody.put("message", "로그인 실패: " + e.getMessage());

            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body("로그인 실패: " + e.getMessage());
        }
    }

    @GetMapping("/userProfile")
    public ResponseEntity<UserDto> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication object: {}", authentication);

        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("Authentication is null or not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Object principal = authentication.getPrincipal();
        String email = null;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            email = (String) principal;
        }

        if (email == null || email.equals("anonymousUser")) {
            log.warn("Email is null or anonymousUser");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        log.info("Authenticated user email: {}", email);

        UserDto userDto;

        try {
            userDto = userService.getUserProfileByEmail(email);
        } catch (RuntimeException e) {
            log.error("Error fetching user info for email: {}", email, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/userProfile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserUpdateRequest userUpdateRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Object principal = authentication.getPrincipal();
        String email = null;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            email = (String) principal;
        }

        if (email == null || email.equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            userService.updateUserProfile(email, userUpdateRequest);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity
            .ok("유저 정보가 수정되었습니다.");
    }

    @PatchMapping("/cancellation")
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Object pricipal = authentication.getPrincipal();
        String email = null;

        if (pricipal instanceof UserDetails) {
            email = ((UserDetails) pricipal).getUsername();
        } else if (pricipal instanceof String) {
            email = (String) pricipal;
        }

        if (email == null || email.equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            userService.deleteUser(email);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return ResponseEntity.ok("유저가 탈퇴되었습니다.");
    }

}
