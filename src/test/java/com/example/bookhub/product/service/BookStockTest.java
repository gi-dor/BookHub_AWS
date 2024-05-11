package com.example.bookhub.product.service;

import com.example.bookhub.product.dto.BuyForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class BookStockTest {

    @Autowired
    BookService bookService;

    @Autowired
    BuyService buyService;


    @Test
    public void testCounterWithConcurrency() throws InterruptedException {
        int numberOfThreads = 6;
        ExecutorService service = Executors.newFixedThreadPool(10); // 스레드 풀을 생성
        CountDownLatch latch = new CountDownLatch(numberOfThreads); // 스레드의 완료를 추적

        for (int i = 0; i < 6; i++) {
            System.out.println("i : "+i);
            // service.execute()는 작업을 스레드 풀에 제출하고, 해당 작업이 완료될 때까지 기다리지 않고 다음 코드로 진행
            // 이후 작업은 백그라운드에서 별도의 스레드에서 실행

            service.execute(() -> { //  메서드를 호출할 때마다 새로운 작업이 스레드 풀에서 실행
                BuyForm buyForm = new BuyForm();
                buyForm.setBuyBookNoList(Arrays.asList(9791196222695L, 9791196222696L));
                buyForm.setBuyBookCountList(Arrays.asList(1, 1));
                buyService.updateBookStock(buyForm);
                latch.countDown();
            });
        }
        service.shutdown();

        latch.await(); // 모든 작업이 완료될 때까지 대기합니다.
        System.out.println("now stock :"+ bookService.getBookByBookNo(9791196222695L).getStock());

        assertEquals(0, bookService.getBookByBookNo(9791196222695L).getStock());
        assertEquals(0, bookService.getBookByBookNo(9791196222696L).getStock());
    }
}
