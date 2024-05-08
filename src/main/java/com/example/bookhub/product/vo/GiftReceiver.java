package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import com.example.bookhub.user.vo.UserDelivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftReceiver {

    private long giftReceiverNo;
    private String name;
    private String tel;
    private String email;
    private UserDelivery userDelivery;
    private Gift gift;
    private User user;
}
