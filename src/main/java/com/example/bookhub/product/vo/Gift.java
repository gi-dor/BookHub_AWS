package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gift {

    private long giftNo;
    private String senderName;
    private String sendMethod;
    private Date giftDate;
    private Buy buy;
}
