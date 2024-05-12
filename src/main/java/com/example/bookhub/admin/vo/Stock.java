package com.example.bookhub.admin.vo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Stock {
    private long notificationNo;
    private long bookNo;
    private Date createdDate;
    private Date completedDate;
    private String completedYn;
    private String publisherName;
    private int stock;
    private int stockNotification;
}
