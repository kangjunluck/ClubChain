package com.blockback.init.service;

import java.security.SecureRandom;
import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.blockback.init.entity.User;
import com.blockback.init.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmailService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    private MimeMessage createPwdMessage(String to, String code)throws Exception{
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to); //보내는 대상
        message.setSubject("Club Chain 임시 비밀번호: " + code); //제목

        String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">임시 비밀번호 발급</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 임시 비밀번호로 다시 로그인해주세요.</p>";
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

    public boolean findPassowrd(User user) throws Exception { // 비밀번호 찾기
        String pwd = createPwd();

        user.setPassword(passwordEncoder.encode(pwd));
        userRepository.save(user);

        MimeMessage message = createPwdMessage(user.getUserEmail(), pwd);

        try {
            emailSender.send(message); // 메일 전송
            return true;
        } catch(MailException es) {//예외처리
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    private String createPwd() { // 임시 비밀번호 숫자+문자 랜덤 10자리 생성
        StringBuffer key = new StringBuffer();
        SecureRandom random = new SecureRandom ();

        for (int i = 0; i < 10; i++) { // 임시 비밀번호 10자리
            if(random.nextBoolean()) {
                key.append((char)((int)(random.nextInt(26))+97));
            } else {
                key.append((random.nextInt(10)));
            }
        }
        return key.toString();
    }
}
