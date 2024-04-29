package com.example.bookhub.board.vo;

import com.example.bookhub.admin.vo.Admin;
import com.example.bookhub.user.vo.User;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InquiryComment {

    private Long no;
    private Inquiry inquiryNo;
    private Admin adminNo;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
