package com.example.bookhub.main.service;

import com.example.bookhub.main.mapper.AladinMapper;
import com.example.bookhub.main.vo.Aladin;
import com.example.bookhub.main.vo.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AladinService {
    private final RestTemplate restTemplate;
    private final AladinMapper aladinMapper;

    // 알라딘 api는 한번에 최대 200개의 데이터만 가져올 수 있다.
    public void fetchItemsFromAladin() {
        String baseUrl = "https://www.aladin.co.kr/ttb/api/ItemList.aspx";
        String apiKey = "ttbdlwldmsgo1703001";
        String queryType = "Bestseller";
        int maxResults = 200;
        String searchTarget = "Book";
        String output = "js";
        String version = "20131101";

        for (int i = 0; i < 5; i++) {
            int start = i * maxResults;
            String apiUrl = baseUrl + "?ttbkey=" + apiKey + "&QueryType=" + queryType + "&MaxResults=" + maxResults + "&start=" + start + "&SearchTarget=" + searchTarget + "&output=" + output + "&Version=" + version;

            ResponseEntity<Aladin> responseEntity = restTemplate.getForEntity(apiUrl, Aladin.class);
            Aladin aladin = responseEntity.getBody();
            List<Aladin.Item> items = aladin.getItem();
            saveItems(items);
        }


    }

    private void saveItems(List<Aladin.Item> items) {
        for (Aladin.Item item : items) {
            Book book = mapToBook(item);
            aladinMapper.insertBook(book);
        }
    }

    private Book mapToBook(Aladin.Item item) {
        Book book = new Book();
        book.setTitle(item.getTitle());
        book.setPubDate(item.getPubDate());
        book.setDescription(item.getDescription());
        book.setPriceStandard(item.getPriceStandard());
        book.setBestRank(item.getBestRank());
        return book;
    }


}
