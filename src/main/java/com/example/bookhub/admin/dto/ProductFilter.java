package com.example.bookhub.admin.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilter {
    private String opt;
    private String keyword;
    private long topCategoryNo;
    private long secondCategoryNo;
    private long thirdCategoryNo;
    private String dateOpt;
    private String moreDate;
    private String lessDate;
    private List<String> status;
    private int moreStock;
    private int lessStock;
    private long publisherNo;
    private String period;
    private int stockOpt;
    private int rows;
    private String sort;

    public List<String> getStatus() {
        if (status.isEmpty()) {
            return null;
        }
        return status;
    }
}
