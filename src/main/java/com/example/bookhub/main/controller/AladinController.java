package com.example.bookhub.main.controller;

import com.example.bookhub.main.service.AladinService;
import com.example.bookhub.main.vo.Aladin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AladinController {
    private final AladinService aladinService;
    private final RestTemplate restTemplate;

    @GetMapping("/aladin")
    public Aladin getAladinXmlResponse() {
        // 알라딘 OpenAPI의 엔드포인트 URL 및 TTBKey

        // 베스트셀러(50) 소설 리스트
        String apiUrl = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1&CategoryId=1&output=js&Version=20131101";

        // API에 GET 요청을 보내고 XML 응답을 받아옴
        Aladin aladin = restTemplate.getForObject(apiUrl, Aladin.class);

        List<Aladin.Item> items = aladin.getItem();
        aladinService.saveItems(items);
        return null;
    }
}



