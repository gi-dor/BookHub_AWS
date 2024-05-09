package com.example.bookhub.product.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.example.bookhub.product.dto.BuyForm;
import com.example.bookhub.product.dto.KakaoReadyResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

@SpringBootTest
public class PayRetryTest {

    @Mock
    private RestTemplate restTemplateMock;

    @Autowired
    private KakaoPayService kakaoPayService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        kakaoPayService.setRestTemplate(restTemplateMock);
    }

    @Test
    public void testRetryLogic_Success() throws Exception {
        // Configure restTemplateMock to throw RestClientException on the first two attempts
        when(restTemplateMock.postForObject(any(URI.class), any(HttpEntity.class), eq(KakaoReadyResponse.class)))
                .thenThrow(new RuntimeException("Simulated exception")) // First attempt
                .thenThrow(new RuntimeException("Simulated exception")) // Second attempt
                .thenReturn(new KakaoReadyResponse()); // Third attempt, success

        // Call the method that has retry logic
        BuyForm buyForm = new BuyForm();
        buyForm.setBuyBookNoList(Arrays.asList(9791196222702L, 9791196222705L));
        buyForm.setFinalPrice(100000);
        kakaoPayService.kakaoPayReady(buyForm);

        verify(restTemplateMock, times(3)).postForObject(any(URI.class), any(HttpEntity.class), eq(KakaoReadyResponse.class));
    }
}
