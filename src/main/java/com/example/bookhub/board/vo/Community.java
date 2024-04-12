package com.example.bookhub.board.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Community {

    private Long no;
    private String user;
    private String title;
    private String content;
    private boolean deleteYn;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
