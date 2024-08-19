package com.crazyspace_edu.api.config;

import com.crazyspace_edu.api.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {

    private final User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자 권한을 반환합니다.
        return null; // 실제 구현 시 권한을 반환하도록 수정하세요.
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    // User 객체를 반환하는 메서드
    public User getUser() {
        return user;
    }
}


