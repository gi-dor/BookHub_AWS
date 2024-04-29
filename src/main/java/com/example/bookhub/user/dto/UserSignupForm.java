package com.example.bookhub.user.dto;

import com.example.bookhub.user.vo.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Arrays;
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


    @Pattern(regexp = "^[A-Za-z0-9]{5,20}$" , message ="알맞은 형식이 아닙니다")    // message = "아이디는 영어 소문자와 숫자만 사용하여 5~20자리여야 합니다."
    private String id;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$"  , message ="비밀번호의 알맞은 형식이 아닙니다")
    private String password;

    // @Pattern(regexp = "$expression") 정규식검증
    @Pattern(regexp="^[가-힣]{2,}$" ,  message ="알맞은 이름의형식이 아닙니다")
    private String name;

    @NotBlank(message = "이메일은 필수입력값입니다.")
    private String email1;
    private String email2;


//   @Pattern(regexp="^\\d{2,3}-\\d{3,4}-\\d{4}$" , message = "유효한 전화번호 형식이 아닙니다")

    private  String tel1;

    @Pattern(regexp="^[0-9]{4}$" , message = "숫자만 입력 가능합니다")
    private  String tel2;

    @Pattern(regexp="^[0-9]{4}$" , message = "숫자만 입력 가능합니다")
    private  String tel3;

    private String zipCode;
    private String address;
    private String addressDetail;


    public User toEntity(PasswordEncoder passwordEncoder) {
        User user = new User();

        user.setId(id);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setEmail(email1 + "@" + email2);
        user.setTel(tel1 + "-" + tel2 + "-" + tel3 );
        user.setZipCode(zipCode);
        user.setAddress(address);
        user.setAddressDetail(addressDetail);

        return user;
    }
}
