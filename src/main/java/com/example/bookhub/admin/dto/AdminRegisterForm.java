package com.example.bookhub.admin.dto;

import com.example.bookhub.admin.vo.Admin;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class AdminRegisterForm {

    private String id;

    private String password;

    private String email;

    private String tel;

    public Admin toAdmin(){
        return Admin.builder()
                .id(id)
                .password(password)
                .email(email)
                .tel(tel)
                .build();
    }

}
