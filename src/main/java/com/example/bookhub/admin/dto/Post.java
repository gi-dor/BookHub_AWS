package com.example.bookhub.admin.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Post {
    private long no;
    private String boardType;
    private String title;
    private String content;
    private String writer;
    private long adminNo;
    private int views;
    private String important;
    private Date createdDate;
    private Date updatedDate;

}
