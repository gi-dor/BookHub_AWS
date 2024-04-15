package com.example.bookhub.product.service;


import com.example.bookhub.product.dto.KakaoApproveResponse;
import com.example.bookhub.product.dto.KakaoReadyResponse;
import groovy.util.logging.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
@Transactional
@Log
public class KakaoPayService {
    private static final String Host = "https://kapi.kakao.com";

    @Value("${pay.admin-key}")
    private String kakaoAdminKey;

    private KakaoReadyResponse kakaoReadyResponse;

    /**
     * 결제 준비
     */
    public String kakaoPayReady() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory()); // 정확한 에러 파악을 위해 생성

        // 서버 요청 헤더
        HttpHeaders headers = getHeaders();

        // 서버 요청 본문
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("cid", "TC0ONETIME"); // 가맹점 코드 - 테스트용
        params.add("partner_order_id", "1001"); // 주문 번호
        params.add("partner_user_id", "lucy"); // 회원 아이디
        params.add("item_name", "책"); // 상품 명
        params.add("quantity", "1"); // 상품 수량
        params.add("total_amount", "20000"); // 상품 가격
        params.add("tax_free_amount", "100"); // 상품 비과세 금액
        params.add("approval_url", "http://localhost:8080/kakaoPay/success"); // 성공시 url
        params.add("cancel_url", "http://localhost:8080/kakaoPay/cancel"); // 실패시 url
        params.add("fail_url", "http://localhost:8080/kakaoPay/fail");

        // 헤더와 바디 붙이기
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoReadyResponse = restTemplate.postForObject(
                    new URI(Host + "/v1/payment/ready"),
                    requestEntity,
                    KakaoReadyResponse.class);

            return kakaoReadyResponse.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "product/pay/error";
    }


    /**
     * 결제 완료 승인
     */
    public KakaoApproveResponse approveResponse(String pgToken) {

        // 서버 요청 헤더
        HttpHeaders headers = getHeaders();

        // 서버 요청 본문
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoReadyResponse.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", "lucy");
        params.add("pg_token", pgToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                Host + "/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);

        return approveResponse;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "KakaoAK " + kakaoAdminKey); // 어드민 키
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
}
