package com.example.bookhub.admin.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Admin {

    private long no;
    private String id;
    private String password;
    private String email;
    private String tel;


}

