package com.example.bookhub.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CacheType {
    REVIEW_GETREVIEWSBYBOOKNO("ReviewMapper.getReviewsByBookNo", 10, 10000),
    CART_FINDCARTLIST("CartMapper.findCartList", 10, 10000);

    private final String cacheName;
    private final int expiredAfterWrite;
    private final int maximumSize;
}
