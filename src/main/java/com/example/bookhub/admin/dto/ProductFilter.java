package com.example.bookhub.admin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilter {
    private String opt;
    private String keyword;
    private String topCategory;
    private String secondCategory;
    private String thirdCategory;
    private String period;
    private String moreDate;
    private String lessDate;
    private String available;
    private String discontinued;
    private String soldOut;
    private int moreStock;
    private int lessStock;
    private long publisher;

}
