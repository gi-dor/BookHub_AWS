package com.example.bookhub.board.vo;

import com.example.bookhub.user.vo.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Attendance {

    private long no;
    private User user;
    private LocalDateTime checkDate;
    private int count;
    private boolean checkYn;

}
