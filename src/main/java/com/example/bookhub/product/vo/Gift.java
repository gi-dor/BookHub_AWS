package com.example.bookhub.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gift {

    private long giftNo;
    private String senderName;
    private String sendMethod;
    private String comment;
    private LocalDateTime date;
    private Buy buy;
}
