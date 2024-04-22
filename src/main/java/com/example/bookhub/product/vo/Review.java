package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Review {

    private long reviewNo;
    private String comment;
    private float rate;
    private int recommendCount;
    private int accuseCount;
    private ReviewTag reviewTag;
    private int replyCount;
    private String buyer;
    private User user;
    private Book book;

    @Builder
    public Review(long reviewNo, String comment, float rate, int recommendCount, int accuseCount, ReviewTag reviewTag,
                  int replyCount, String buyer, User user, Book book) {
        super();
        this.reviewNo = reviewNo;
        this.comment = comment;
        this.rate = rate;
        this.recommendCount = recommendCount;
        this.accuseCount = accuseCount;
        this.reviewTag = reviewTag;
        this.replyCount = replyCount;
        this.buyer = buyer;
        this.user = user;
        this.book = book;
    }
}
