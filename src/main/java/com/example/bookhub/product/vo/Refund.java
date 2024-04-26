package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Refund {

    private long refundNo;
    private LocalDateTime refundDate;
    private Buy buy;
    private User user;
    private RefundReason refundReason;
}
