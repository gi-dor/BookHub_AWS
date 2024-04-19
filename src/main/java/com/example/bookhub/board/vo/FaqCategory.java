package com.example.bookhub.board.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaqCategory {

    private Long no;
    private String name;

    public FaqCategory(Long no) {
        this.no = no;
    }
}
