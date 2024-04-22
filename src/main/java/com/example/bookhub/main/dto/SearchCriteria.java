package com.example.bookhub.main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {
    private String opt;
    private String keyword;
    private String extraKeyword;
    private String extraTitleOpt;
    private String extraAuthorOpt;
    private String extraPublisherOpt;
    private String sort;
}
