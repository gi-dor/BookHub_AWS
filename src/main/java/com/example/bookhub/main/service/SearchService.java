package com.example.bookhub.main.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.example.bookhub.main.vo.Aladin;
import com.example.bookhub.main.vo.Book;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service


public class SearchService {
    /*api에서 데이터 불러오는 코드
    private final RestTemplate restTemplate;

    public List<Book> searchBooks(String query) {
        String baseUrl = "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx";
        String apiKey = "ttbdlwldmsgo1703001";
        String queryType = "Title"; // 제목 검색으로 가정
        int maxResults = 200;
        int start = 1; // 시작 인덱스
        String searchTarget = "Book";
        String output = "js";
        String version = "20131101";

        // API 호출
        String apiUrl = baseUrl + "?ttbkey=" + apiKey + "&Query=" + query + "&QueryType=" + queryType +
                        "&MaxResults=" + maxResults + "&start=" + start + "&SearchTarget=" + searchTarget +
                        "&output=" + output + "&Version=" + version;

        ResponseEntity<Aladin> responseEntity = restTemplate.getForEntity(apiUrl, Aladin.class);
        Aladin aladin = responseEntity.getBody();

        System.out.println("API Response: " + aladin);

        if (aladin != null && aladin.getItem() != null) {
            // Aladin.Item 객체를 Book 객체로 변환하여 리스트에 저장
            List<Book> books = new ArrayList<>();
            for (Aladin.Item item : aladin.getItem()) {
                Book book = mapToBook(item);
                books.add(book);
            }
            return books;
        } else {
            return null;
        }
    }

    private Book mapToBook(Aladin.Item item) {
        Book book = new Book();
        book.setCover(item.getCover());
        book.setTitle(item.getTitle());
        book.setAuthor(item.getAuthor());
        book.setPubDate(item.getPubDate());
        book.setDescription(item.getDescription());
        book.setPriceStandard(item.getPriceStandard());
        return book;
    }

*/


}



