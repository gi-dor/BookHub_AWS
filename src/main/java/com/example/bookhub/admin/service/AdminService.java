package com.example.bookhub.admin.service;

import com.example.bookhub.admin.dto.AdminRegisterForm;
import com.example.bookhub.admin.mapper.AdminMapper;
import com.example.bookhub.admin.vo.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;


    public Admin getAdmin(String id){
        return adminMapper.getAdmin(id);
    }

    public Admin join(AdminRegisterForm form){
        // DB에서 중복되는 아이디가 있는지 확인하지 않고 그냥 빌드
        Admin admin = form.toAdmin();

        // 비밀번호 암호화과정
        admin.setPassword("$2a$10$WYUZUL2uX0yZNZ9CoZGrreYy3GL9Lk/9C.pWTbT0lQLROb3t2iQi6");
        adminMapper.join(admin);

        return admin;




    }
}
