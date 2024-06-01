package com.elice.ggorangjirang.global.oauth2.service;

import com.elice.ggorangjirang.global.oauth2.CustomOAuth2User;
import com.elice.ggorangjirang.global.oauth2.OAuthAttributes;
import com.elice.ggorangjirang.users.entity.Users;
import com.elice.ggorangjirang.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("CustomOAuth2UserSerivce.loadUser() 실행 - OAuth2 로그인 요청 진입");

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest); // OAuth 서비스에서 가져온 유저 정보를 담고 있는 유저

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // OAuth2 로그인 시 키(PK)가 되는 값
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                                                  .getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

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
     * 만약 찾은 회원이 있다면, 그대로 반환하고 없다면 saveUser()를 호출하여 회원을 저장
     */
    private Users getUser(OAuthAttributes attributes) {
        Users findUser = userRepository.findBySocialId(attributes.getOAuth2UserInfo().getId()).orElse(null);

        if (findUser == null) {
            return saveUser(attributes);
        }
        return findUser;
    }

    // 생성된 User 객체를 DB에 저장 : socialId, email, role 값만 있는 상태
    private Users saveUser(OAuthAttributes attributes) {
        Users createdUser = attributes.toEntity(attributes.getOAuth2UserInfo());
        return userRepository.save(createdUser);
    }
}
