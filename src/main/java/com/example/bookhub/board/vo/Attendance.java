package com.example.bookhub.board.vo;

import com.example.bookhub.user.vo.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;

@Data
public class Attendance {

    private long no;
    @JsonIgnore
    private User user;
    @JsonIgnore
    private LocalDateTime checkDate;
    public String getDay() {
        return checkDate.format(DateTimeFormatter.ofPattern("d"));
    }
}
