package com.csn.csn.auth.service;

import com.csn.csn.auth.EmailAuth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailAuthServiceImpl implements EmailAuthService {

    private final String fromAddress = "no-reply@no-reply.com";
    private final MailSender mailSender;

    @Override
    public void generateAuthCodeEmail(HttpSession httpSession) {
        EmailAuth emailAuth = new EmailAuth();
        httpSession.setAttribute("emailAuth", emailAuth);

        String toAddress = (String)httpSession.getAttribute("email");
        log.info("보내는 사람 = {}", fromAddress);
        log.info("받는 사람 = {}", toAddress);
        sendEmail(toAddress, fromAddress, "게이버 인증코드", emailAuth.getAuthCode());
    }

    private void sendEmail(String toAddress, String fromAddress, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(fromAddress);
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        log.info("이메일 전송 완료 : {}", toAddress);
        mailSender.send(simpleMailMessage);
    }
}
