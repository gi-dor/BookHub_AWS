package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.AdminRegisterForm;
import com.example.bookhub.admin.exception.AlreadyAdminEmailException;
import com.example.bookhub.admin.exception.AlreadyAdminIdException;
import com.example.bookhub.admin.mapper.AdminMapper;
import com.example.bookhub.admin.vo.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    private final PasswordEncoder passwordEncoder;


    public Admin getAdmin(String id){
        return adminMapper.getAdminId(id);
    }

    public Admin join(AdminRegisterForm form){
        // DB에서 중복되는 아이디가 있는지 확인
        Admin saveAdmin = adminMapper.getAdminId(form.getId());
        if(saveAdmin != null){
            throw new AlreadyAdminIdException("[" + form.getId() +"]는 이미 사용중인 아이디입니다");
        }

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
