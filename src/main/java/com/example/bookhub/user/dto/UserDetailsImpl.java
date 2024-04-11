package com.example.bookhub.user.dto;

import java.util.Collection;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
public class UserDetailsImpl implements UserDetails {
// 사용자의 인증 정보를 내타내는데 사용되며  사용자가 제공한 자격이 클래스의 정보와 일치하는지 확인한다

    private String id;
    private String password;
    private  Collection<? extends GrantedAuthority> authorities;

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
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
        return true;
    }
}
