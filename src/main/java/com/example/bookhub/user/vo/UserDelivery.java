package com.example.bookhub.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDelivery {

    private Long no;
    private String name;
    private String tel;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String defaultAddressYn;
    private User user;
    public String getFullAddress() {
        return "우편번호 : "+zipCode + "    |  주소 :"  + address + " ,    " + addressDetail;
    }

}
