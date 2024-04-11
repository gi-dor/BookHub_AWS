package com.example.bookhub.board.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notice {

    private Long no;
    private String title;
    private String content;
    private boolean deleteYn;
    private int priority;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
