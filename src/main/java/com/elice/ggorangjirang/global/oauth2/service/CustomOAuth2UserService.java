package com.elice.ggorangjirang.global.oauth2.service;

import com.elice.ggorangjirang.global.oauth2.CustomOAuth2User;
import com.elice.ggorangjirang.global.oauth2.OAuthAttributes;
import com.elice.ggorangjirang.global.oauth2.userinfo.OAuth2UserInfo;
import com.elice.ggorangjirang.jwt.service.JwtService;
import com.elice.ggorangjirang.users.entity.Role;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RestTemplate restTemplate;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("CustomOAuth2UserSerivce.loadUser() 실행 - OAuth2 로그인 요청 진입");

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest); // OAuth 서비스에서 가져온 유저 정보를 담고 있는 유저

        // String registrationId = userRequest.getClientRegistration().getRegistrationId(); // 소셜 로그인 추가시 쓸 변수

        // OAuth2 로그인 시 키(PK)가 되는 값
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                                                  .getUserInfoEndpoint().getUserNameAttributeName();


        Map<String, Object> attributes = oAuth2User.getAttributes();
        log.info("OAuth2User attributes: {}", attributes);

        OAuthAttributes extractAttributes = OAuthAttributes.ofKakao(userNameAttributeName, attributes);

        Users createdUser = getUser(extractAttributes);

        return new CustomOAuth2User(
            Collections.singletonList(new SimpleGrantedAuthority(createdUser.getRole().getKey())),
            attributes,
            extractAttributes.getNameAttributeKey(),
            createdUser.getEmail(),
            createdUser.getRole()
        );
    }

    /*
     * attributes에 들어있는 소셜 로그인의 식별값 id를 통해 회원을 찾아 반환하는 메소드
     * 만약 찾은 회원이 있다면, 그대로 반환하고 없다면 addUser()를 호출하여 회원을 저장
     */
    public Users getUser(OAuthAttributes attributes) {
        Users findUser = userRepository.findBySocialId(attributes.getOAuth2UserInfo().getId()).orElse(null);

        if (findUser == null) {
            return addUser(attributes);
        }
        return findUser;
    }

    // 생성된 User 객체를 DB에 저장 : email, role 값만 있는 상태
    public Users addUser(OAuthAttributes attributes) {
        Users createdUser = attributes.toEntity();
        createdUser.setRole(Role.USER);

        String refreshToken = jwtService.createRefreshToken();
        createdUser.updateRefreshToken(refreshToken);

        return userRepository.save(createdUser);
    }

    public String getAccessToken(String authorizationCode) {
        String tokenUri = "https://kauth.kakao.com/oauth/token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "YOUR_KAKAO_CLIENT_ID"); // 실제 카카오 클라이언트 ID로 대체
        params.add("redirect_uri", "YOUR_REDIRECT_URI"); // 실제 리다이렉트 URI로 대체
        params.add("code", authorizationCode);
        params.add("client_secret", "YOUR_CLIENT_SECRET"); // 클라이언트 시크릿이 필요한 경우 추가

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUri, request, Map.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            return (String) responseBody.get("access_token");
        } else {
            throw new RuntimeException("Failed to get access token from Kakao");
        }
    }

    public OAuthAttributes getUserInfo(String accessToken) {
        String userInfoUri = "https://kapi.kakao.com/v2/user/me";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(userInfoUri, HttpMethod.GET, request, Map.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            Map<String, Object> kakaoAccount = (Map<String, Object>) responseBody.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            return OAuthAttributes.builder()
                .nameAttributeKey("id")
                .oAuth2UserInfo(new OAuth2UserInfo(responseBody))
                .email((String) kakaoAccount.get("email"))
                .name((String) profile.get("nickname"))
                .build();
        } else {
            throw new RuntimeException("Failed to get user info from Kakao");
        }
    }

    public Users addOrUpdateUser(OAuthAttributes attributes) {
        Users user = userRepository.findByEmail(attributes.getEmail())
            .map(entity -> entity.update(attributes.getName()))
            .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
