package com.example.bookhub.user.dto;

import com.example.bookhub.user.vo.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserSignupForm {
// 회원 가입 양식을 처리한다 , 사용자 등록할 때 쓰인다

    @NotBlank(message = "ID는 필수 입력값 입니다")
    @Pattern(regexp = "^[a-z0-9]{5,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 5~20자리여야 합니다.")
    private String id;

    @NotBlank(message= "비밀번호는 필수 입력 값 입니디")
    @Size(min = 8 , message = "비밀번호는 8글자 이상 입니다")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    // @Pattern(regexp = "$expression") 정규식검증
    @NotBlank(message = "이름은 필수 입력 값입니다")
    @Pattern(regexp="^[가-힣]{2,}$", message = "이름은 한글 2글자 이상")
    private String name;

    @NotBlank(message="이메일은 필수 입력 값입니다")
    @Email(message = "유효한 이메일 형식이 아닙니다")
    private String email;

    @NotBlank(message = "전화번호는 필수 입력 값입니다")
    @Pattern(regexp="^\\d{2,3}-\\d{3,4}-\\d{4}$" , message = "유효한 전화번호 형식이 아닙니다")
    private String tel;

    private String zipCode;
    private String address;
    private String addressDetail;


    public User toEntity(PasswordEncoder passwordEncoder) {
        User user = new User();

        user.setId(id);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setEmail(email);
        user.setTel(tel);
        user.setZipCode(zipCode);
        user.setAddress(address);
        user.setAddressDetail(addressDetail);

        return user;
    }
}
