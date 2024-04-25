package com.example.bookhub.main.mapper;

import com.example.bookhub.main.vo.Aladin;
import com.example.bookhub.product.vo.Author;
import com.example.bookhub.product.vo.Book;
import com.example.bookhub.product.vo.Publisher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface AladinMapper {

    void insertPublisher(Publisher publisher);
    Publisher getPublisherByName(String name);

    void insertAuthor(Author author);
    Author getAuthorByName(String name);

    void insertBook(Book book);

    void insertBookAuthor(Map<String, Object> map );

    void insertBookImage(Map<String, Object> img);
}


