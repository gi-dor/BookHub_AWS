package com.example.bookhub.user.util;

import com.example.bookhub.admin.exception.AlreadyAdminEmailException;
import com.example.bookhub.user.dto.UserSignupForm;
import com.example.bookhub.user.vo.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
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

    // 회원가입 완료시에 실행되는 회원가입완료 이메일 보내기
    public void sendEmail(String to, String subject, String html) throws MessagingException {
//        SimpleMailMessage message = new SimpleMailMessage();
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html , true );
            javaMailSender.send(message);
    }

   // 긁어와서 사용한 코드라 자세히 모름;
    public String registerHtmlTemplate(UserSignupForm form) throws Exception {

        ClassPathResource resource = new ClassPathResource("templates/user/mail/registerEmail.html");
        String htmlTemplate = null;

        try{
            htmlTemplate = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        } catch (IOException ex) {
            System.err.println("이메일 템플릿을 로드하는 도중 오류가 발생했습니다.");
            ex.printStackTrace();
        }
        return htmlTemplate.replace("NAME", form.getName());
    }


    public String resetPasswordTemplate(String password) throws Exception{

        ClassPathResource resource = new ClassPathResource("templates/user/mail/resetPassword.html");
        String htmlTemplate = null;
        try{
            htmlTemplate = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

        } catch (IOException ex) {
            System.err.println("이메일 템플릿을 로드하는 도중 오류가 발생했습니다.");
            ex.printStackTrace();
        }
        return htmlTemplate.replace("PASSWORD", password);
    }


}

