package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Review {

    private long reviewNo;
    private String comment;
    private float rate;
    private int recommendCount;
    private int accuseCount;
    private Date createdDate;
    private Date updatedDate;
    private ReviewTag reviewTag;
    private int replyCount;
    private String buyerYn;
    private User user;
    private Book book;

    @Builder
    public Review(long reviewNo, String comment, float rate, int recommendCount, int accuseCount, Date createdDate, Date updatedDate,
                  ReviewTag reviewTag, int replyCount, String buyerYn, User user, Book book) {
        super();
        this.reviewNo = reviewNo;
        this.comment = comment;
        this.rate = rate;
        this.recommendCount = recommendCount;
        this.accuseCount = accuseCount;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.reviewTag = reviewTag;
        this.replyCount = replyCount;
        this.buyerYn = buyerYn;
        this.user = user;
        this.book = book;
    }
}
