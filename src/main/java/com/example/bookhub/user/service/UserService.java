package com.example.bookhub.user.service;

import com.example.bookhub.user.dto.UserDetailsImpl;
import com.example.bookhub.user.dto.UserSignupForm;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.util.MailService;
import com.example.bookhub.user.util.RandomPassword;
import com.example.bookhub.user.vo.User;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;


    /*
    사용자의 id 를 기반으로 사용자의 정보를 로드한다
    해당 사용자의 정보를 UserDetails 객체로 반환하는데 , 실제 사용자의 정보를 포함하며
    Spring Security 는 사용자의 인증 , 권한 부여를 처리한다
     */
    /*
    사용자 이름을 기반으로 사용자를 찾는다.
    실제 구현에서는 검색이 대소문자를 구분하거나 구분하지 않을 수 있으며, 이는 구현 인스턴스가 구성된 방식에 따라 다를 수 있다.
    사용자 이름을 식별하는 사용자의 데이터가 필요하다
    사용자 레코드가 완전히 채워진 사용자 레코드를 반환. 반환된 객체는 null이 아니다
    사용자를 찾을 수 없거나 사용자에게 부여된 권한이 없는 경우 UsernameNotFoundException을 발생
     */

    /**
     * 주어진 사용자 아이디를 기준으로 사용자의 데이터를 가져와 UserDetails 객체로 반환합니다.
     * @param id 사용자 아이디
     * @return UserDetails 객체
     * @throws UsernameNotFoundException 주어진 아이디에 해당하는 사용자를 찾을 수 없는 경우 발생합니다.
     */
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        // 사용자 아이디를 기준으로 데이터베이스에서 사용자 정보를 가져옵니다. 이 정보는 user 객체에 저장
        User user = userMapper.selectUserById(id);

        // 데이터베이스에서 가져온 사용자 정보가 없다면(null이면) 예외를 발생시킵니다.
        if(user == null) {
            throw new UsernameNotFoundException("등록되지 않은 사용자이거나 탈퇴처리된 사용자입니다. : " +id);
        }

        if ("Y".equals(user.getDelYn())) {
            throw new UsernameNotFoundException("회원탈퇴 처리된 계정입니다 : " +id);
        }


        // UserDetailsImpl 클래스의 객체를 생성합니다. 이 객체는 사용자의 인증 및 권한 정보를 제공하기위해 사용한다
        UserDetailsImpl userDetails = new UserDetailsImpl();

        // 객체에서 가져온 사용자 아이디와 비밀번호를 userDetails 객체에 설정
        userDetails.setId(user.getId());
        userDetails.setPassword(user.getPassword());

        userDetails.setAuthorities(List.of(new SimpleGrantedAuthority("ROLE_USER")));

        System.out.println("서비스 - 로그인한 아이디 : " +user.getId());
        return userDetails;
    }


    /**
     * 주어진 회원가입 폼 UserSignupForm 으로 사용자를 등록한다
     * @param form 사용자 회원가입 폼 UserSignupForm
     * @return 등록된 사용자
     * @throws RuntimeException 이미 존재하는 아이디나 이메일일 경우 발생
     */
    public User registerUser(UserSignupForm form) throws Exception {
        long beforeTime = System.currentTimeMillis();

        String to = form.getEmail1() + "@" + form.getEmail2();
        String subject = "회원 가입이 완료되었습니다.";
        String html = mailService.registerHtmlTemplate(form); // load
        mailService.sendEmail(to, subject, html);

        if (userMapper.selectUserById(form.getId()) != null) {
            throw new RuntimeException("이미 존재하는 아이디입니다: " + form.getId());
        }

        User user = form.toEntity(passwordEncoder);
        userMapper.insertUser(user);

        long afterTime = System.currentTimeMillis();
        long diffTime = afterTime - beforeTime;
        log.info("동기 회원가입 작업 총 실행 시간: " + diffTime + "ms");

        return user;
    }

    // 회원가입 - 이메일 보내기 ( 비동기 사용하기 )
    public User registerUserAsync(UserSignupForm form) throws Exception {
        long beforeTime = System.currentTimeMillis();

        CompletableFuture<Boolean> future = mailService.sendEmailAsync(form);
        future.thenAccept(result -> {
           if (result) {
               System.out.println("회원가입 완료 메일이 성공적으로 발송되었습니다.");
           }  else {
               System.out.println("회원가입 완료 메일이 발송실패하였습니다.");
           }
        });

        if (userMapper.selectUserById(form.getId()) != null) {
            throw new RuntimeException("이미 존재하는 아이디입니다: " + form.getId());
        }

        User user = form.toEntity(passwordEncoder);
        userMapper.insertUser(user);

        long afterTime = System.currentTimeMillis();
        long diffTime = afterTime - beforeTime;

        log.info("::::   비동기 작업 총 실행 시간: " + diffTime + "ms");

        return user;
    }





    /**
     * 주어진 번호에 해당하는 사용자를 데이터베이스에서 선택
     * @param no 사용자 번호
     * @return 선택된 사용자
     * @throws RuntimeException 주어진 번호에 해당하는 사용자를 찾을 수 없는 경우 발생
     */
    public User selectUserByNo(Long no) {
        User user = userMapper.selectUserByNo(no);

        if (user == null) {
            throw new RuntimeException("해당 번호에 해당하는 사용자를 찾을 수 없습니다: " + no);
        }
        return user;
    }

    /**
     * 주어진 아이디에 해당하는 사용자를 데이터베이스에서 찾는다
     * @param id 사용자 아이디
     * @return 선택된 사용자
     * @throws RuntimeException 주어진 아이디에 해당하는 사용자를 찾을 수 없는 경우 발생
     */
    public User selectUserById(String id) {
        System.out.println("userService.selectUserById = " +id);
        User user = userMapper.selectUserById(id);
        if (user == null) {
            throw new RuntimeException("해당 아이디에 해당하는 사용자를 찾을 수 없습니다: " + id);
        }
        return user;
    }



    /**
     * id 중복 체크
     * @param id DB에 저장되어있는  아이디
     * @return 0  또는 1
     */
    public int idCheck(String id) {
        int cnt = userMapper.idCheck(id);
        System.out.println("IdCnt : "+cnt);
        return cnt;
    }


    /**
     * email 중복 체크
     * @param email DB에 저장되어있는  이메일
     * @return 0 또는 1
     */
    public int emailCheck(String email) {
        int cnt = userMapper.emailCheck(email);
        System.out.println("EmailCnt : " + cnt);
        return cnt;
    }

    /**
     *  사용자 정보를 수정하는 메서드
     * @param user 수정할 사용자 정보를 담은 User 객체
     * @return 업데이트 (수정)된 사용자 정보를  담은 User 객체
     */
    public User modifyUserInfo(User user) {
        userMapper.updateUser(user);

        return user;
    }


    /**
     * 주어진 이메일에 해당하는 사용자를 데이터베이스에서 찾는다
     * @param email 사용자 이메일
     * @return 선택된 사용자
     * @throws RuntimeException 주어진 이메일에 해당하는 사용자를 찾을 수 없는 경우 발생
     */
    public User selectUserByIdAndEmail(String id, String email) {
        User user = userMapper.selectUserByIdAndEmail(id,email);
        if (user == null) {
            throw new RuntimeException("해당 이메일에 해당하는 사용자를 찾을 수 없습니다: " + email);
        }
        return user;
    }


    // 예외 발생하면 롤백되서 이전 상태로 가게하려고 - ACID
    @Transactional
    public String resetPassword(String id , String email) {

        User user = userMapper.selectUserByIdAndEmail(id, email);

        if (user == null) {
            throw new IllegalArgumentException("주어진 정보에 해당하는 사용자 정보가 없습니다.");
        } else if (id == null || id.isEmpty() || email == null || email.isEmpty()) {
            throw new IllegalArgumentException("아이디와 이메일을 모두 입력해야 합니다.");
        }

        // 임시 비밀번호 만들기
        String resetPassword = RandomPassword.generatePassword(8,16);

        // 비밀번호 변경 - 암호화 안되서 DB에 그냥 비밀번호 저장되버림
        userMapper.updateResetPassword(id,passwordEncoder.encode(resetPassword));

        return resetPassword;
    }


}

