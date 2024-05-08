package com.example.bookhub.admin.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Posts {
    private long no;
    private String boardType;
    private String title;
    private String content;
    private String writer;
    private int view;
    private int priority;
    private Date createdDate;
    private Date updatedDate;

}
