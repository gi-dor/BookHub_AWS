package com.example.bookhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 빈 설정
@EnableWebSecurity // 스프링 시큐리티 웹보안 활성화
@EnableMethodSecurity(prePostEnabled = true , securedEnabled = true)
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        // csrf 공격을 방지하는 기술 비활성화
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest.requestMatchers("/**").permitAll());


        // 폼기반 로그인을 활성화
        // 사용자가 로그인 한다면 /user/login 경로로 이동하게되는데 로그인 성공시 ("/") 로 리다이렉트
        http.formLogin(formLogin -> formLogin.loginPage("/user/login").usernameParameter("id").defaultSuccessUrl("/"));


        // 로그아웃
        // HTTP 세션을 무효화해서 로그인 상태를 제거한다
        http.logout(logout -> logout.logoutUrl("/user/logout").logoutSuccessUrl("/").invalidateHttpSession(true));
        return http.build();
    }


    // 비밀번호 암호화
    // salt 사용
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
