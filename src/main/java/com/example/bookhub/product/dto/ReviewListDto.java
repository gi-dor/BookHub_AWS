package com.example.bookhub.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Data
@ToString
public class ReviewListDto {

    private List<ReviewDto> reviewDtoList;
    private Pagination pagination;
}
