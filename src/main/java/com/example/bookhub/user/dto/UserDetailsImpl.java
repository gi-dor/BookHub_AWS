package com.example.bookhub.user.dto;

import java.util.Collection;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
public class UserDetailsImpl implements UserDetails {
// 사용자의 인증 정보를 내타내는데 사용되며  사용자가 제공한 자격이 클래스의 정보와 일치하는지 확인한다
//  Spring Security에서 사용자의 상세 정보를 나타내는 클래스

// 사용자의 아이디와 비밀번호를 포함해 여러 정보를 제공하는데 사용된다 ?

    private String id;
    private String password;
    private  Collection<? extends GrantedAuthority> authorities;

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // 사용자의 비밀번호를 반환
    @Override
    public String getPassword() {
        return password;
    }

    // 사용자의  아이디를 반환
    @Override
    public String getUsername() {
        return id;
    }

    // 사용자 계정이 만료되지 않았는지 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 사용자 계정이 잠겨있지 않은지 여부를 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 사용자의 인증 정보가 만료되지 않았는지 여부를 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 사용자 계정이 활성화되어 있는지 여부를 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}
