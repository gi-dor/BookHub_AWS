package com.example.bookhub.product.service;


import com.example.bookhub.product.dto.BuyForm;
import com.example.bookhub.product.dto.KakaoApproveResponse;
import com.example.bookhub.product.dto.KakaoCancelResponse;
import com.example.bookhub.product.dto.KakaoReadyResponse;
import com.example.bookhub.product.exception.BookHubException;
import com.example.bookhub.product.exception.kakaoPay.KakaoPayApproveException;
import com.example.bookhub.product.exception.kakaoPay.KakaoPayReadyException;
import com.example.bookhub.user.mapper.UserMapper;
import com.example.bookhub.user.vo.User;
import groovy.util.logging.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

@Service
@RequiredArgsConstructor
@Transactional
@Log
public class KakaoPayService {
    private static final String Host = "https://kapi.kakao.com";

    @Value("${pay.admin-key}")
    private String kakaoAdminKey;

    private KakaoReadyResponse kakaoReadyResponse;
    private KakaoApproveResponse kakaoApproveResponse;
    private KakaoCancelResponse kakaoCancelResponse;
    private RestTemplate restTemplate;

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * 결제 준비
     */
    @Retryable(
            value = RuntimeException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )
    public String kakaoPayReady(BuyForm buyForm, String userId)  {

        //restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory()); // 정확한 에러 파악을 위해 생성

        // 서버 요청 헤더
        HttpHeaders headers = getHeaders();

        // 서버 요청 본문
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("cid", "TC0ONETIME"); // 가맹점 코드 - 테스트용
        params.add("partner_order_id", "1001"); // 주문 번호
        params.add("partner_user_id", userId); // 회원 아이디
        params.add("item_name", "북허브 도서"); // 상품 명
        params.add("quantity", String.valueOf(buyForm.getBuyBookNoList().size())); // 상품 수량
        params.add("total_amount", String.valueOf(buyForm.getFinalPrice())); // 상품 가격
        params.add("tax_free_amount", "0"); // 상품 비과세 금액
        params.add("approval_url", "http://52.79.119.109:8080/kakaoPay/success"); // 성공시 url
        params.add("cancel_url", "http://52.79.119.109:8080/kakaoPay/cancel"); // 실패시 url
        params.add("fail_url", "http://52.79.119.109:8080/kakaoPay/fail");

        // 헤더와 바디 붙이기
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoReadyResponse = restTemplate.postForObject(
                    new URI(Host + "/v1/payment/ready"),
                    requestEntity,
                    KakaoReadyResponse.class);
        } catch (RestClientException e) {
            throw new KakaoPayReadyException("카카오 결제 준비 실패");
        } catch (URISyntaxException e) {
            throw new KakaoPayReadyException("카카오 결제 준비 실패");
        }

        return kakaoReadyResponse.getNext_redirect_pc_url();
    }


    /**
     * 결제 완료 승인
     */
    public KakaoApproveResponse approveResponse(String pgToken, String userId) {

        // 서버 요청 헤더
        HttpHeaders headers = getHeaders();

        // 서버 요청 본문
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoReadyResponse.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", userId);
        params.add("pg_token", pgToken);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        try {
            kakaoApproveResponse = restTemplate.postForObject(
                    Host + "/v1/payment/approve",
                    requestEntity,
                    KakaoApproveResponse.class);
        } catch(RestClientException e){
            throw new KakaoPayApproveException("카카오 결제 승인 실패");
        }

        return kakaoApproveResponse;
    }

    /**
     * 결제 환불
     *
     */
    public KakaoCancelResponse kakaoCancel(String orderId, int refundPrice) {

        // 카카오페이 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", "TC0ONETIME");
        parameters.add("tid", orderId);
        parameters.add("cancel_amount", String.valueOf(refundPrice));
        parameters.add("cancel_tax_free_amount", "0"); // 환불 비과세 금액 일단 0으로 설정
        parameters.add("cancel_vat_amount", "0"); // 환불 부가세 금액 일단 0으로 설정

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        try {
            kakaoCancelResponse = restTemplate.postForObject(
                    "https://kapi.kakao.com/v1/payment/cancel",
                    requestEntity,
                    KakaoCancelResponse.class);
        } catch(RestClientException e){
            throw new KakaoPayApproveException("이미 주문취소/환불된 주문입니다");
        }

        return kakaoCancelResponse;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Authorization", "KakaoAK " + kakaoAdminKey); // 어드민 키
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }

    @Recover
    public String recover(RuntimeException e, BuyForm buyForm) {
        throw new BookHubException(e.getMessage());
    }
}
