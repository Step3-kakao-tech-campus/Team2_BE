package com.example.team2_be.auth.mail;

import com.example.team2_be.auth.AuthRequest;
import com.example.team2_be.core.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailService {
    private final JavaMailSender javaMailSender;
    private final RedisUtils redisUtils;

    public String createKey() {
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);

            switch(index) {
                case 0:
                    key.append((char) (rnd.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) (rnd.nextInt(26) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }

        return key.toString();
    }

    public void sendSimpleMessage(String to) throws Exception {

        String authCode = createKey(); // 랜덤 인증번호 생성

        MimeMessage message = createMessage(to, authCode);

        try {
            javaMailSender.send(message);
            redisUtils.setDataExpire(to, authCode, 60 * 5L);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public MimeMessage createMessage(String to, String authCode) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);// 보내는 대상
        message.setSubject("네모 회원가입 이메일 인증");// 제목

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<h1> 네모 회원가입 인증번호 메일입니다.</h1>";
        msgg += "<br>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += authCode + "</strong><div><br/> "; // 메일에 인증번호 넣기
        msgg += "</div>";
        message.setText(msgg, "utf-8", "html");// 내용, charset 타입, subtype
        // 보내는 사람의 이메일 주소, 보내는 사람 이름
        message.setFrom(new InternetAddress("nemo_auth@naver.com", "네모"));// 보내는 사람

        return message;
    }

    public void checkAuthCode(AuthRequest.CheckMailDTO checkMailDTO) {
        String findAuthCode = redisUtils.getData(checkMailDTO.getEmail());

        if(findAuthCode == null) {
            throw new IllegalArgumentException("인증 객체를 찾지 못하였습니다.");
        }

        if(!findAuthCode.equals(checkMailDTO.getAuthCode())) {
            throw new IllegalArgumentException("인증번호가 불일치 합니다.");
        }

        redisUtils.deleteData(checkMailDTO.getEmail()); //인증 후 인증 객체 삭제
    }
}
