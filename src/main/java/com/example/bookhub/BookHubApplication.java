package com.example.bookhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.example.bookhub")
@EnableCaching
@EnableRetry
@MapperScan("com.example.bookhub.*.mapper") // 여기에 MyBatis Mapper 인터페이스가 위치한 패키지를 지정
public class BookHubApplication {

    public static void main(String[] args) {

        System.out.println("hello world");
        SpringApplication.run(BookHubApplication.class, args);

    }

}
