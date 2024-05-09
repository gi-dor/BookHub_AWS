package com.example.bookhub.admin.dto;

import lombok.extern.slf4j.Slf4j;
import jakarta.mail.*;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSend {

    private final JavaMailSender javaMailSender;

    // 메일보내기 테스트용
    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("제목칸");    //  제목
        message.setText("내용입니다");          // 내용
        message.setTo("ftsand@naver.com");    // 받는 사람

        javaMailSender.send(message);
    }

    // MimeMessage 전달 성공
    public void sendEmail2(String to) throws MessagingException {
        long startTime = System.currentTimeMillis();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        // 메일 제목
        mimeMessageHelper.setSubject("북허브 판매자센터 가입이 완료되었습니다");
        // 메일 수신자
        mimeMessageHelper.setTo(to);
        // 메일 내용
        mimeMessageHelper.setText("북허브 판매자센터 가입을 축하드립니다.");
        javaMailSender.send(mimeMessage);

        long endTime = System.currentTimeMillis();
        long finishTime = endTime - startTime;

        //log.info(":::    이메일 총 작업 소요 시간 " + finishTime + "ms");
    }

    // 비동기방식
    @Async
    public CompletableFuture<Boolean> sendEmailAsync(String to){
        long startTime = System.currentTimeMillis();

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setSubject("[비동기]북허브 판매자센터 가입이 완료되었습니다");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText("[비동기]북허브 판매자센터 가입을 축하드립니다.");
            javaMailSender.send(mimeMessage);

            long endTime = System.currentTimeMillis();
            long finishTime = endTime - startTime;
            log.info("이메일 전송이 완료되었습니다. 총 작업 소요 시간: " + finishTime + "ms");

            return CompletableFuture.completedFuture(true); // 이메일 전송 성공
        } catch (MessagingException e) {
            log.error("이메일 전송 중 오류가 발생했습니다: " + e.getMessage());
            return CompletableFuture.completedFuture(false); // 이메일 전송 실패
        }
    }

}





