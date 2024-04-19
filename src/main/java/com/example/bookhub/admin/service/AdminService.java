package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.AdminRegisterForm;
import com.example.bookhub.admin.exception.AlreadyAdminEmailException;
import com.example.bookhub.admin.exception.AlreadyAdminIdException;
import com.example.bookhub.admin.mapper.AdminMapper;
import com.example.bookhub.admin.vo.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;


    public Admin login(String id, String password){
        // Id와 비밀번호 받아오기
        Admin findId = adminMapper.getAdminId(id);
        String findPw = (findId == null) ? "" : findId.getPassword();


        // DB에 id가 존재하지 않거나 저장된 비밀번호가 일치하지 않는 경우를 체크
        if((findId == null) || passwordEncoder.matches(password, findPw) == false) {// 입력된 비밀번호와 저장된 비밀번호를 비교하는 메소드
            return null;
        }

        return findId;
    }

    public Admin join(AdminRegisterForm form){
        // DB에서 중복되는 아이디가 있는지 확인
        Admin saveAdmin = adminMapper.getAdminId(form.getId());
        if(saveAdmin != null){
            throw new AlreadyAdminIdException("[" + form.getId() +"]는 이미 사용중인 아이디입니다");
        }
        // DB에 중복되는 이메일이 있는지 확인
        saveAdmin = adminMapper.getAdminEmail(form.getEmail());
        if(saveAdmin != null){
            throw new AlreadyAdminEmailException("[" + form.getEmail() +"]는 이미 사용중인 이메일입니다");
        }

        Admin admin = form.toAdmin();

        // 비밀번호 암호화과정
        String encodePassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
        adminMapper.join(admin);

        return admin;

    }
}
