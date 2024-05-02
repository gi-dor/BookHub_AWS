package com.example.bookhub.admin.dto;

import java.util.ArrayList;
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
    private String available;
    private String discontinued;
    private String soldOut;
    private List<String> status;
    private int moreStock;
    private int lessStock;
    private long publisherNo;
    private String period;
    private int stockOpt;
    private int rows;
    private String sort;

    public List<String> getStatus() {
        status = new ArrayList<>();

        if (available != null) {
            status.add(available);
        }
        if (discontinued != null) {
            status.add(discontinued);
        }
        if (soldOut != null) {
            status.add(soldOut);
        }

        if (status.isEmpty()) {
            return null;
        }

        return status;
    }
}
