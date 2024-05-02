package com.example.bookhub.board.vo;

import com.example.bookhub.admin.vo.Admin;
import lombok.*;


import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InquiryComment {

    private Long no;
    private Inquiry inquiry;
    private Admin admin;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
