package com.example.bookhub.main.service;


import com.example.bookhub.main.vo.Book;
import java.util.List;


public interface SearchService {
    List<Book> getPubDate(String keyword);

    int booksGetTotal();
}





