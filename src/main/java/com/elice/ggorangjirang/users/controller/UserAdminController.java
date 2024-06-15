package com.elice.ggorangjirang.users.controller;

import com.elice.ggorangjirang.global.login.service.LoginService;
import com.elice.ggorangjirang.users.dto.UserAdminDto;
import com.elice.ggorangjirang.users.dto.UserDto;
import com.elice.ggorangjirang.users.dto.UserLoginDto;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import com.elice.ggorangjirang.users.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class UserAdminController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final LoginService loginService;

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserAdminDto> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin-users/admin-users";
    }

    // JSON 데이터를 반환하는 API 엔드포인트
    @GetMapping("/api")
    @ResponseBody
    public List<UserAdminDto> getAllUsersApi() {
        return userService.getAllUsers();
    }

    @PatchMapping("/api/{email}/cancellation")
    @ResponseBody
    public UserAdminDto deleteUser(@PathVariable String email) {
        Users user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        user.delete();
        userRepository.save(user);

        return userService.converAdminToDto(user);
    }

    @GetMapping("/login")
    public String login() {
        return "admin-login/admin-login";
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
                .body(responseBody);
        }
    }

}
