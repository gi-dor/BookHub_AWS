package com.example.bookhub.admin.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderFilter {
    private String opt;
    private String keyword;
    private String dateOpt;
    private String period;
    private String moreDate;
    private String lessDate;
    private List<String> status;
    private int rows;

    public List<String> getStatus() {
        // status.isEmpty()가 null.isEmpty()인 경우 발생하는 예외를 방지하기 위함
        if (status == null) {
            return status;
        }

        if (status.isEmpty()) {
            return null;
        }
        return status;
    }
}
