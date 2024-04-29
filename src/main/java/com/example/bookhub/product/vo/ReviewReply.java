package com.example.bookhub.product.vo;

import com.example.bookhub.user.vo.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ReviewReply {

    private long reviewReplyNo;
    private String comment;
    private Date updatedDate;
    private Review review;
    private User user;

    @Builder
    public ReviewReply(long reviewReplyNo, String comment, Review review, User user) {
        super();
        this.reviewReplyNo = reviewReplyNo;
        this.comment = comment;
        this.review = review;
        this.user = user;
    }
}
