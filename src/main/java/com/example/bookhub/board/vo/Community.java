package com.example.bookhub.board.vo;

import com.example.bookhub.user.vo.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Community {

    private Long no;
    private User user;
    private String title;
    private String content;
    private boolean deleteYn;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private CommunityImages images;
    private CommunityComment comment;
}
