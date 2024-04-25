package com.example.bookhub.admin.dto;

import com.example.bookhub.product.vo.Category;
import com.example.bookhub.user.vo.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IndividualDto {

    private int individualNo;
    private Category categoryName;
    private User userName;
    private String individualTitle;
    private LocalDateTime individualUpdateDate;
}
