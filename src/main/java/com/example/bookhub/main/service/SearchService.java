package com.example.bookhub.main.service;


import com.example.bookhub.main.dto.BookListDto;
import com.example.bookhub.main.dto.SearchCriteria;


public interface SearchService {
    BookListDto searchBooks(SearchCriteria searchCriteria);



}





