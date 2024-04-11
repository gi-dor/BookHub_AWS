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
        //String apiUrl1 = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1&CategoryId=1&output=js&Version=20131101";

        // 베스트셀러(50) 수험서 리스트
        //String apiUrl2 = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1108&CategoryId=1383&output=js&Version=20131101";

        // 베스트셀러(50) 사회과학 리스트
        String apiUrl = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1108&CategoryId=798&output=js&Version=20131101apiUrl";

        // 베스트셀러(50) 만화 리스트
        //String apiUrl4 = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1108&CategoryId=76001&output=js&Version=20131101";

        // 베스트셀러(50) 가정/요리/뷰티 리스트
        //String apiUrl5 = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1108&CategoryId=1230&output=js&Version=20131101";

        // 베스트셀러(50) 경제/경영 리스트
        //String apiUrl6 = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1108&CategoryId=170&output=js&Version=20131101";

        // 베스트셀러(50) 고등학교 참고서 리스트
        //String apiUrl7 = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1108&CategoryId=76001&output=js&Version=20131101";

        // 베스트셀러(50) 고전 리스트
        //String apiUrl8 = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1108&CategoryId=76001&output=js&Version=20131101";

        // 베스트셀러(50) 과학 리스트
        //String apiUrl9 = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1108&CategoryId=987&output=js&Version=20131101";

        // 베스트셀러(50) 대학교재/전문서적 리스트
        //String apiUrl10 = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1108&CategoryId=8257&output=&Version=20131101";

        // 베스트셀러(50) 에세이 리스트
        //String apiUrl11 = "https://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlwldmsgo1703001&QueryType=Bestseller&MaxResults=50&start=1108&CategoryId=55889&output=js&Version=20131101";

        // 베스트셀러(50) 사회과학 리스트
        //String apiUrl12 = "";

        // API에 GET 요청을 보내고 XML 응답을 받아옴
        Aladin aladin = restTemplate.getForObject(apiUrl, Aladin.class);


        List<Aladin.Item> items = aladin.getItem();
        aladinService.saveItems(items);
        return null;
    }
}



