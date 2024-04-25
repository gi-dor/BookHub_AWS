package com.example.bookhub.user.dto;

import com.example.bookhub.product.vo.Author;
import com.example.bookhub.product.vo.Book;
import com.example.bookhub.product.vo.BookImages;
import java.util.List;

public class WishListDTO {

    private Long no;
    private Book book;
    List<Author> authorList;
    private BookImages bookImages;
}

