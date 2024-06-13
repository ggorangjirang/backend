package com.elice.ggorangjirang.global.oauth2;

import com.elice.ggorangjirang.global.oauth2.userinfo.OAuth2UserInfo;
import com.elice.ggorangjirang.users.entity.Role;
import com.elice.ggorangjirang.users.entity.Users;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OAuthAttributes {

    private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값
    private OAuth2UserInfo oAuth2UserInfo;
    private String email;
    private String name;

    @Builder
    private OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo, String email, String name) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
        this.email = email;
        this.name = name;
    }

    public static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        OAuth2UserInfo userInfo = new OAuth2UserInfo(attributes);

        return OAuthAttributes.builder()
            .nameAttributeKey(userNameAttributeName)
            .oAuth2UserInfo(new OAuth2UserInfo(attributes))
            .email(userInfo.getEmail())
            .name(userInfo.getName())
            .build();
    }

    public Users toEntity() {
        return Users.builder()
            .socialId(oAuth2UserInfo.getId())
            .email(oAuth2UserInfo.getEmail())
            .name(oAuth2UserInfo.getName())
            .role(Role.GUEST)
            .build();
    }

}
