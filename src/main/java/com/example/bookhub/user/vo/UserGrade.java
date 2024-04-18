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
public class UserGrade {

    private Long no;
    private String name;
    private Double benefit;
    private Long amount;
}
