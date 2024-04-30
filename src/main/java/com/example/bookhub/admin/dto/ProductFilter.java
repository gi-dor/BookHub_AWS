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
    private String period;
    private String moreDate;
    private String lessDate;
    private String available;
    private String discontinued;
    private String soldOut;
    private int moreStock;
    private int lessStock;
    private long publisherNo;
    List<String> statusList;

    public List<String> getStatusList() {
        if (available != null || discontinued != null || soldOut != null) {
            statusList = new ArrayList<>();
        }
        if (available != null) {
            statusList.add(available);
        }
        if (discontinued != null) {
            statusList.add(discontinued);
        }
        if (soldOut != null) {
            statusList.add(soldOut);
        }

        return statusList;
    }
}
