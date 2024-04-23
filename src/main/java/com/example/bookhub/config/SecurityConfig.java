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
        http.csrf(csrf -> csrf.disable());   // csrf 방지 - CSRF 공격은 사용자가 의도하지 않은 요청을 악의적으로 전송하는 것을 막는데 사용
        http.cors(cors -> cors.disable());   // cors 방지 - 다른 도메인에서의 리소스 요청을 허용하거나 차단하는 보안 메커니즘
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
