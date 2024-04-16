package com.example.bookhub.admin.dto;

import com.example.bookhub.admin.vo.Admin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class AdminRegisterForm {

    @NotEmpty(message = "ID를 입력해주세요")
    @Pattern(regexp = "^[a-z0-9]{4,12}$", message = "아이디는 영문 소문자와 숫자 4~12자리여야 합니다.")
    private String id;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    private String password_confirm;

    @NotEmpty(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식이 올바르지 않습니다")
    private String email;

    @NotEmpty(message = "휴대폰 번호를 입력해주세요")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$",
            message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
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
