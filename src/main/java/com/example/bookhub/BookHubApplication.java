package com.example.bookhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableCaching
@EnableRetry
public class BookHubApplication {

    public static void main(String[] args) {

        System.out.println("hello world");
        SpringApplication.run(BookHubApplication.class, args);

    }

}
