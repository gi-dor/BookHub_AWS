package com.example.bookhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync    // 스프링에 비동기처리 활성화
@Configuration
public class AsyncConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);    // 스레드 풀의 기본크기
        executor.setMaxPoolSize(30);    // 스레드 풀의 최대 크기
        executor.setQueueCapacity(50);  // 작업 대기열의 용량 설정 , 대기열이 가득차면 추가 작업 거부
        executor.initialize();          // 작업 완료후 스레드 풀 초기화
        return executor;
    }
}