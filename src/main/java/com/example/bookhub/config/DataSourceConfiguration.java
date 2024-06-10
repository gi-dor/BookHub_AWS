package com.example.bookhub.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;

@Slf4j
@Configuration
public class DataSourceConfiguration {

    private static final String MASTER_SERVER = "MASTER"; // 마스터 서버를 나타내는 상수 문자열입니다.
    private static final String REPLICA_SERVER = "REPLICA"; // 복제 서버를 나타내는 상수 문자열입니다.

    @Bean
    @Qualifier(MASTER_SERVER)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create() // 'spring.datasource.master'로 시작하는 구성 속성을 사용하여 DataSource를 작성합니다.
                .build();
    }

    @Bean
    @Qualifier(REPLICA_SERVER)
    @ConfigurationProperties(prefix = "spring.datasource.replica")
    public DataSource replicaDataSource() {
        return DataSourceBuilder.create() // 'spring.datasource.replica'로 시작하는 구성 속성을 사용하여 DataSource를 작성합니다.
                .build();
    }

    @Bean
    public DataSource routingDataSource(
            @Qualifier(MASTER_SERVER) DataSource masterDataSource,
            @Qualifier(REPLICA_SERVER) DataSource replicaDataSource
    ) {
        RoutingDataSource routingDataSource = new RoutingDataSource(); // 사용자 정의 RoutingDataSource 인스턴스를 생성합니다.

        HashMap<Object, Object> dataSourceMap = new HashMap<>(); // 데이터 소스를 보관할 맵을 생성합니다.
        dataSourceMap.put("master", masterDataSource); // "master" 키로 마스터 DataSource를 맵에 넣습니다.
        dataSourceMap.put("replica", replicaDataSource); // "replica" 키로 복제 DataSource를 맵에 넣습니다.

        routingDataSource.setTargetDataSources(dataSourceMap); // 라우팅을 위한 대상 데이터 소스를 설정합니다.
        routingDataSource.setDefaultTargetDataSource(masterDataSource); // 기본 대상 데이터 소스를 마스터 DataSource로 설정합니다.

        return routingDataSource; // routingDataSource를 반환합니다.
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        DataSource determinedDataSource = routingDataSource(masterDataSource(), replicaDataSource()); // 라우팅 로직을 기반으로 적절한 DataSource를 결정합니다.
        return new LazyConnectionDataSourceProxy(determinedDataSource); // 지연 연결 DataSource 프록시를 반환합니다.
    }

}
