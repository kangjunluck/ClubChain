package com.blockback.init.service;

import java.security.SecureRandom;
import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender emailSender;

    private MimeMessage createMessage(String to, String code)throws Exception{
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to); //보내는 대상
        message.setSubject("Club Chain 확인 코드: " + code); //제목

        String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 Club Chain 가입 창이 있는 브라우저 창에 입력하세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += code;
        msg += "</td></tr></tbody></table></div>";

        message.setText(msg, "utf-8", "html"); //내용
        message.setFrom(new InternetAddress("","ClubChain")); //보내는 사람 구글이메일을 입력해야한다.

        return message;
    }

    // 인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        SecureRandom random = new SecureRandom ();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((random.nextInt(10)));
        }
        return key.toString();
    }

    public String sendEmail(String to) throws Exception {

        String code = createKey(); // 인증코드 만들기
        MimeMessage message = createMessage(to, code); // 메일 내용

        try {
            emailSender.send(message); // 메일 전송
            return code;
        } catch(MailException es) {//예외처리
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

}
