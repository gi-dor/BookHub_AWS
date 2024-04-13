package com.example.bookhub.board.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Faq {

    private Long no;
    private FaqCategory faqCategory;
    private String title;
    private String content;
    private boolean deleteYn;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
