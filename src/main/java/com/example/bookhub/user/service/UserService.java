package com.example.bookhub.user.service;

import com.example.bookhub.user.dto.UserDetailsImpl;
import com.example.bookhub.user.dto.UserSignupForm;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    /*
    사용자의 id 를 기반으로 사용자의 정보를 로드한다
    해당 사용자의 정보를 UserDetails 객체로 반환하는데 , 실제 사용자의 정보를 포함하며
    Spring Security 는 사용자의 인증 , 권한 부여를 처리한다
     */
    /*
    사용자 이름을 기반으로 사용자를 찾습니다. 실제 구현에서는 검색이 대소문자를 구분하거나 구분하지 않을 수 있으며, 이는 구현 인스턴스가 구성된 방식에 따라 다를 수 있습니다.
    사용자 이름을 식별하는 사용자의 데이터가 필요합니다.
    사용자 레코드가 완전히 채워진 사용자 레코드를 반환합니다. 반환된 객체는 null이 아닙니다.
    사용자를 찾을 수 없거나 사용자에게 부여된 권한이 없는 경우 UsernameNotFoundException을 발생시킵니다.
     */

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        // 사용자 아이디를 기준으로 데이터베이스에서 사용자 정보를 가져옵니다. 이 정보는 user 객체에 저장
        User user = userMapper.selectUserById(id);

        // 데이터베이스에서 가져온 사용자 정보가 없다면(null이면) 예외를 발생시킵니다.
        if(user == null) {
            throw new UsernameNotFoundException("Id 찾을수 없습니다 : " +id);
        }

        // UserDetailsImpl 클래스의 객체를 생성합니다. 이 객체는 사용자의 인증 및 권한 정보를 제공하기위해 사용한다
        UserDetailsImpl userDetails = new UserDetailsImpl();

        // 객체에서 가져온 사용자 아이디와 비밀번호를 userDetails 객체에 설정
        userDetails.setId(user.getId());
        userDetails.setPassword(user.getPassword());

        userDetails.setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_USER")));

        return userDetails;
    }


    public User registerUser(UserSignupForm form) {

        if (userMapper.selectUserById(form.getId()) != null) {
            throw new RuntimeException("이미 존재하는 아이디입니다: " + form.getId());
        }

        if (userMapper.selectUserByEmail(form.getEmail()) != null) {
            throw new RuntimeException("이미 존재하는 이메일 입니다: " + form.getEmail());
        }

        User user = form.toEntity(passwordEncoder);
        userMapper.insertUser(user);

        return user;
    }

    public User selectUserByNo(Long no) {
        User user = userMapper.selectUserByNo(no);

        if (user == null) {
            throw new RuntimeException("해당 번호에 해당하는 사용자를 찾을 수 없습니다: " + no);
        }
        return user;
    }

    public User selectUserById(String id) {
        User user = userMapper.selectUserById(id);
        if (user == null) {
            throw new RuntimeException("해당 아이디에 해당하는 사용자를 찾을 수 없습니다: " + id);
        }
        return user;
    }

    public User selectUserByEmail(String email) {
        User user = userMapper.selectUserByEmail(email);
        if (user == null) {
            throw new RuntimeException("해당 이메일에 해당하는 사용자를 찾을 수 없습니다: " + email);
        }
        return user;
    }


}

