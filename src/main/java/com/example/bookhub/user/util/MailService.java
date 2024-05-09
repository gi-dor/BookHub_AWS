package com.example.bookhub.user.util;

import com.example.bookhub.user.dto.UserSignupForm;
import com.example.bookhub.user.exception.UserMailSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender javaMailSender;

    // 테스트용 진짜 심플이메일
    public void sendSimpleEmail() {
        // Spring에서 제공하는 단순한 문자 메일을 보낼수 있는 객체
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("임시비밀번호가 도착했습니다");
        message.setTo("rltjs3563@naver.com");
        message.setText("임시비밀번호 : 808");

        javaMailSender.send(message);
    }


    @Async
    public CompletableFuture<Boolean> sendEmailAsync(UserSignupForm form)  {
        long startTime = System.currentTimeMillis();
        try {
            String to = form.getEmail1() + "@" + form.getEmail2();
            String subject = "회원 가입이 완료되었습니다.";
            String html = registerHtmlTemplate(form); // load

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            javaMailSender.send(message);

            long endTime = System.currentTimeMillis();

            long finishTime = endTime - startTime;
            log.info(":::    이메일 총 작업 소요 시간 " + finishTime + "ms");

            // 이메일 전송 작업이 완료되면 결과를 반환
            // 호출자에게 이메일 전송이 성공적으로 완료되었음을 알려줌
            return CompletableFuture.completedFuture(true);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(false);
        }
    }

    // 이메일 보내기
    public void sendEmail(String to, String subject, String html)  {
        try {
            long startTime = System.currentTimeMillis();
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            javaMailSender.send(message);

            long endTime = System.currentTimeMillis();
            long finishTime = endTime - startTime;
            log.info(":::    이메일 총 작업 소요 시간 " + finishTime + "ms");
        } catch (MessagingException ex) {
            throw new UserMailSendException(ex.getMessage());
        }

    }


    // 긁어와서 사용한 코드라 자세히 모름;
    public String registerHtmlTemplate(UserSignupForm form) {

        ClassPathResource resource = new ClassPathResource("templates/user/mail/registerEmail.html");
        String htmlTemplate = null;

        try {
            htmlTemplate = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new UserMailSendException("이메일 템플릿을 로드하는 도중 오류가 발생했습니다.");
        }
        return htmlTemplate.replace("NAME", form.getName());
    }

    public String resetPasswordTemplate(String password)  {

        ClassPathResource resource = new ClassPathResource("templates/user/mail/resetPassword.html");
        String htmlTemplate = null;
        try {
            htmlTemplate = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        } catch (IOException ex) {
            System.err.println("이메일 템플릿을 로드하는 도중 오류가 발생했습니다.");
            ex.printStackTrace();
        }
        return htmlTemplate.replace("PASSWORD", password);
    }

}

