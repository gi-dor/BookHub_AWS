package com.example.bookhub.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockFilter {
    private String opt;
    private String keyword;
    private String dateOpt;
    private String moreDate;
    private String lessDate;
    private String period;
    private int rows;

}
