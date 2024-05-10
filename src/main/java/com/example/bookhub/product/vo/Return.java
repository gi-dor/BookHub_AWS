package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.Before;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Return {

    private long returnNo;
    private LocalDateTime returnDate;
    private Buy buy;
    private User user;
    private ReturnReason returnReason;
    private int price;
    private String type;
}
