package com.elice.ggorangjirang.global.login.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

// JWT 로그인 실패 시 처리하는 핸들러
@Slf4j
@RequiredArgsConstructor
public class LoginFailHandler extends SimpleUrlAuthenticationFailureHandler {

}
