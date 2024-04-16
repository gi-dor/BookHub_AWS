package com.example.bookhub.main.service;

import com.example.bookhub.main.vo.Book;
import org.springframework.stereotype.Service;
import com.example.bookhub.main.vo.Aladin.Item;
import com.example.bookhub.main.mapper.AladinMapper;

import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AladinService {

    private final AladinMapper aladinMapper;

    public void saveItems(List<Item> items) {
        for (Item item : items) {
            // 가져올 데이터 작성
            Book book = new Book();
            book.setTitle(item.getTitle());
            book.setLink(item.getLink());
            book.setAuthor(item.getAuthor());
            book.setPubDate(item.getPubDate());
            book.setDescription(item.getDescription());
            book.setIsbn13(item.getIsbn13());
            book.setItemId(item.getItemId());
            book.setPriceSales(item.getPriceSales());
            book.setPriceStandard(item.getPriceStandard());
            book.setCover(item.getCover());
            book.setCategoryName(item.getCategoryName());
            book.setPublisher(item.getPublisher());
            book.setBestRank(item.getBestRank());

            // 책 객체를 데이터베이스에 저장
            aladinMapper.insertBook(book);
        }
    }
}
