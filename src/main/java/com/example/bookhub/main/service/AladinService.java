package com.example.bookhub.main.service;

import com.example.bookhub.main.mapper.AladinMapper;
import com.example.bookhub.main.vo.Aladin;
import com.example.bookhub.product.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Service
public class AladinService {
    private final RestTemplate restTemplate;
    private final AladinMapper aladinMapper;


    // 알라딘 api는 한번에 최대 200개의 데이터만 가져올 수 있다.
    @Transactional
    public void fetchItemsFromAladin() {

        String baseUrl = "https://www.aladin.co.kr/ttb/api/ItemList.aspx";
        String apiKey = "ttbdlwldmsgo1703001";
        String queryType = "Bestseller";
        String categoryId = "2551";
        int maxResults = 50;
        int start = 4;
        String searchTarget = "Book";
        String output = "js";
        String version = "20131101";


        String apiUrl = baseUrl + "?ttbkey=" + apiKey + "&QueryType=" + queryType + "&MaxResults=" + maxResults + "&start=" + start + "&CategoryId=" + categoryId + "&SearchTarget=" + searchTarget + "&output=" + output + "&Version=" + version;

        ResponseEntity<Aladin> responseEntity = restTemplate.getForEntity(apiUrl, Aladin.class);
        Aladin aladin = responseEntity.getBody();
        List<Aladin.Item> items = aladin.getItem();


        savePublisher(items);
        saveAuthor(items);
        saveBook(items);

    }

    private void saveBook(List<Aladin.Item> items) {
        Category cat = new Category();
        cat.setCategoryNo(21);

        for (Aladin.Item item : items) {
            Book book = new Book();
            book.setName(item.getTitle());
            book.setIsbn(item.getIsbn13());
            book.setDescription(item.getDescription());
            book.setDiscountRate(0.15f);
            book.setDiscontinuingYn('Y');
            book.setPublishedDate(getDate(item.getPubDate()));
            book.setPrice(item.getPriceStandard());
            book.setCategory(cat);
            Publisher p = aladinMapper.getPublisherByName(item.getPublisher());
            book.setPublisher(p);

            aladinMapper.insertBook(book);


            String text = item.getAuthor().substring(0, item.getAuthor().length());
            List<Map<String, Object>> list = getBookAuthor(book.getBookNo(), text);
            System.out.println(list);
            for (Map<String, Object> map : list) {
                aladinMapper.insertBookAuthor(map);
            }

            Map<String, Object> img = new HashMap<>();
            img.put("bookNo", book.getBookNo());
            img.put("cover", item.getCover());

            aladinMapper.insertBookImage(img);

        }
    }

    private Date getDate(String text) {
        try {
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            return f.parse(text);
        } catch (ParseException e) {
            return null;
        }
    }

    private void savePublisher(List<Aladin.Item> items) {
        for (Aladin.Item item : items) {
            Publisher publisher = aladinMapper.getPublisherByName(item.getPublisher());
            if (publisher == null) {

                publisher = new Publisher();
                publisher.setName(item.getPublisher());
                aladinMapper.insertPublisher(publisher);
            }
        }
    }

    public List<Map<String, Object>> getBookAuthor(long bookNo, String text) {
        List<Map<String, Object>> list = new ArrayList<>();
        String[] values = text.split(",");

        for (String value : values) {
            Map<String, Object> map = new HashMap<>();
            map.put("bookNo", bookNo);
            String name;
            if (value.endsWith(")")) {
                name = value.substring(0, value.indexOf("(")).trim();
                String type = value.substring(value.indexOf("(") + 1, value.indexOf(")"));
                map.put("type", type);
            } else {
                name = value.trim();
            }
            Author savedAuthor = aladinMapper.getAuthorByName(name);
            map.put("no", savedAuthor.getAuthorNo());

            list.add(map);
        }

        return list;
    }

    private void saveAuthor(List<Aladin.Item> items) {
        for (Aladin.Item item : items) {
            String text = item.getAuthor().substring(0, item.getAuthor().length());
            String[] values = text.split(",");

            for (String value : values) {
                String name;
                if (value.endsWith(")")) {
                    name = value.substring(0, value.indexOf("(")).trim();
                } else {
                    name = value.trim();
                }
                System.out.println(name);

                Author savedAuthor = aladinMapper.getAuthorByName(name);
                if (savedAuthor == null) {
                    Author author = new Author();
                    author.setName(name.trim());
                    aladinMapper.insertAuthor(author);
                }
            }


        }

    }

}



