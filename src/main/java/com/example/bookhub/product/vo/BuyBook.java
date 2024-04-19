package com.example.bookhub.product.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyBook {

    private long buyNo;
    private long bookNo;
    private int count;

    @Builder
    public BuyBook(long buyNo, long bookNo, int count) {
        this.buyNo = buyNo;
        this.bookNo = bookNo;
        this.count = count;
    }
}
