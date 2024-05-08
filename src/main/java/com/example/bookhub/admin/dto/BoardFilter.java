package com.example.bookhub.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardFilter {
    private String opt;
    private String keyword;
    private char deleteYN;
    private String dateOpt;
    private String period;
    private String moreDate;
    private String lessDate;
    private String boardType;
    private int rows;
    private String sort;
}
