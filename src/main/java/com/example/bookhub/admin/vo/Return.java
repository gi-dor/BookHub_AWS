package com.example.bookhub.admin.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Return {

    private long no;
    private long buyNo;
    private String userName;
    private int buyCount;
    private int price;
    private Date returnDate;
    private Date returnedDate;

}
