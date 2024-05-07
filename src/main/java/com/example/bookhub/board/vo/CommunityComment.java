package com.example.bookhub.board.vo;

import com.example.bookhub.admin.vo.Admin;
import com.example.bookhub.user.vo.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommunityComment {

    private Long no;
    private Community community;
    private User user;
    private Admin admin;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long parentNo;


}
