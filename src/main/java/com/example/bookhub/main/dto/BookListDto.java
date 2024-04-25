package com.example.bookhub.main.dto;

import com.example.bookhub.product.vo.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class BookListDto {
    List<BookDto> books;
    SearchCriteria criteria;

}
