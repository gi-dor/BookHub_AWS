package com.example.bookhub.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {

    BOOK_GETAUTHORBYBOOKNO("BookMapper.getAuthorByBookNo", 10, 10000),
    INQUIRIES("MyPageMapper.cacheInquiries" , 10,10000),
    USER_COUNT("dashBoardMapper.getAllUserCnt", 60, 10000),
    BOOK_COUNT("dashBoardMapper.getAllBookCnt", 60, 10000),
    TOTAL_DATE("dashBoardMapper.getTotalDate", 60, 10000),
    NOANSWER_CNT("dashBoardMapper.noAnswerCnt", 60, 10000 ),
    ANSWER_CNT("dashBoardMapper.answerCnt", 60, 10000 ),
    GET_REVIEWS("dashBoardMapper.getReviews", 60, 10000 ),
    AVERAGE_RATE("dashBoardMapper.averageRate", 60, 10000 ),
    NOANSWER_RATIO("dashBoardMapper.noAnswerRatio", 60, 10000 ),
    ANSWER_RATIO("dashBoardMapper.answerRatio", 60, 10000 ),
    GET_RATIO("dashBoardMapper.getRatios", 60, 10000 );


    private final String cacheName;
    private final int expiredAfterWrite;
    private final int maximumSize;

}
