package com.example.bookhub.user.vo;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDelivery {

    private Long no;
    private String name;
    private String tel;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String defaultAddressYn;
    private User user;

}
