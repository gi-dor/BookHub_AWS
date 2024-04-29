package com.example.bookhub.product.dto;

import com.example.bookhub.product.vo.Book;
import com.example.bookhub.product.vo.ReviewImage;
import com.example.bookhub.product.vo.ReviewReply;
import com.example.bookhub.product.vo.ReviewTag;
import com.example.bookhub.user.vo.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReviewDto {

    private long reviewNo;
    private String comment;
    private float rate;
    private int recommendCount;
    private int accuseCount;
    private Date createdDate;
    private Date updatedDate;
    private String recommended;
    private ReviewTag reviewTag;
    private int replyCount;
    private String buyer;
    private User user;
    private Book book;
    private List<ReviewImage> reviewImageList;
    private List<ReviewReply> reviewReplyList;
}
