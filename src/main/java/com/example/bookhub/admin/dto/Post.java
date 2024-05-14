package com.example.bookhub.admin.dto;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "제목을 입력해주세요.")
    private String title;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
    private String writer;
    private long adminNo;
    private int views;
    private String important;
    private Date createdDate;
    private Date updatedDate;

}
