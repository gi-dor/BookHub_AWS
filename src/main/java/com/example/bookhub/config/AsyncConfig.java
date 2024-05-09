package com.example.bookhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync    // 스프링에 비동기처리 활성화 - @Async 어노테이션 비동기 작동하게 만듬
@Configuration  // 빈등록
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
/*
corePoolSize

        스레드 풀의 기본 크기를 나타내는 값
        최소한의 스레드 갯수
        위에 작성한 코드를 기준으로 최소한 5개의 스레드가 항상 유지된다
        default 1

maximumPoolSize

        스레드 풀의 최대 크기를 나타내는 값
        스레드 풀이 생성할수 있는 최대 스레드 갯수
        스레드 풀이 corePoolSize 까지 스레드를 생성한 이후 , 작업이 도착할 때 마다 새로운 스레드를 생성해 작업을 처리
        maximumPoolSize를 설정하면 스레드 풀의 크기가 동적으로 조절된다

QueueCapacity

        스레드 풀 내부에서 작업을 대기하는 용량을 나타내는 값
        스레드 풀이 처리하지 못한 작업들을 저장하는 대기열의 크기를 결정
        스레드 풀이 처리할 수 있는 작업의 양을 제한하기 위해서
        스레드 풀이 비정상적으로 과도한 작업을 처리하거나 메모리 부족으로인한 문제를 방지할 수 있다
        작게 설정되면 대기열이 가득차 추가작업이 거부 , 너무크면 메모리를 많이 사용해 성능저하가 발생

keepAliveTime

        작업하지 않고 놀고있는 스레드 ( = 비활성 스레드) 가 유지되는 최대시간
        corePoolSize 를 초과하고 maxPoolSize 보다 작은 수로 스레드가 추가적으로 만들어진 경우에만 적용
        스레드 풀의 현재 스레드 갯수가 corePoolSize 보다 크며 , 작업이 도착하지 않아 비활성화 상태인 스레드가 발생하면
        keepAliveTime 이후에는 해당 스레드를 종료시킨다
        default 60sec
*/
