package com.elice.ggorangjirang.global.oauth2;

import com.elice.ggorangjirang.users.entity.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private String email;
    private Role role; // OAuth 처음 로그인인지 확인 여부를 위한 변수
    private String nameAttributeKey;

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            String email, Role role) {
        super(authorities, attributes, nameAttributeKey);
        this.email = email;
        this.role = role;
        this.nameAttributeKey = nameAttributeKey;
    }

    public String getNameAttributeKey() {
        return nameAttributeKey;
    }
}
