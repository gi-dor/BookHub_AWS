package com.example.bookhub.main.vo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
public class Aladin {
    private String title;
    private String link;
    private String pubDate;
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;
    private String query;
    private int searchCategoryId;
    private String searchCategoryName;
    private List<Item> item;

    @Getter
    @Setter
    @ToString
    public static class Item {
        private String title;
        private String link;
        private String author;
        private String pubDate;
        private String description;
        private String isbn13;
        private int itemId;
        private int priceSales;
        private int priceStandard;
        private String cover;
        private String categoryId;
        private String categoryName;
        private String publisher;
        private int bestRank;
    }

}
