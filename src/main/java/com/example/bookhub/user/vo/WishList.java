package com.example.bookhub.user.vo;

import com.example.bookhub.product.vo.Book;
import com.example.bookhub.product.vo.BookImages;
import java.time.LocalDateTime;

public class WishList {

    private Long no;
    private User user;
    private LocalDateTime wishListCreatedDate;
    private Book book;
    private BookImages bookImages;

}
