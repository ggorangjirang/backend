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

    private static final String EMAIL_DOMAIN = "@socialUser.com";
    private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값
    private OAuth2UserInfo oAuth2UserInfo;

    @Builder
    private OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    public static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
            .nameAttributeKey(userNameAttributeName)
            .oAuth2UserInfo(new OAuth2UserInfo(attributes))
            .build();
    }

    public Users toEntity(OAuth2UserInfo oAuth2UserInfo) {
        return Users.builder()
            .socialId(oAuth2UserInfo.getId())
            .email(UUID.randomUUID() + EMAIL_DOMAIN)
            .name(oAuth2UserInfo.getName())
            .role(Role.GUEST)
            .build();
    }

}
