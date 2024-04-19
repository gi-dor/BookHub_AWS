package com.example.bookhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BookHubApplication {

    public static void main(String[] args) {

        System.out.println("hello world");
        SpringApplication.run(BookHubApplication.class, args);

    }

}
