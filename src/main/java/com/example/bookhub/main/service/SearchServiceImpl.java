package com.example.bookhub.main.service;

import com.example.bookhub.main.mapper.SearchMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.bookhub.main.vo.Book;
import java.util.List;


@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final SearchMapper searchMapper;

    @Override
    // 검색결과 리스트
    public List<Book> getSearchList(String keyword) {
        List<Book> books = searchMapper.getSearchList(keyword);
        for (Book book : books) {
            // bookDescription이 100글자를 초과하는 경우 처음 200글자만을 남긴다.
            if (book.getBookDescription() != null && book.getBookDescription().length() > 100) {
                book.setBookDescription(book.getBookDescription().substring(0, 100) + "...");
            }
        }
        return books;
    }


    @Override
    public int booksGetTotal() {
        return searchMapper.bookGetTotal();
    }


}
