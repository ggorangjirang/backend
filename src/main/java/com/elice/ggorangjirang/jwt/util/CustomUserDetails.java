package com.elice.ggorangjirang.jwt.util;

import com.elice.ggorangjirang.users.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final Users users;

    public CustomUserDetails(Users users) {
        this.users = users;
    }

    public Long getUserId() {
        return users.getId();
    }

    // 사용자의 권한을 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
    }

    // 사용자 계정의 유효기간 확인
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 사용자 계정이 잠겨 있는지 확인
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 사용자 자격 증명의 유효기간 확인
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사용자 계정이 활성화되어 있는지 확인
    @Override
    public boolean isEnabled() {
        return users.getDeletedAt() == null;
    }

    public Users getUsers(){
        return users;
    }
}
