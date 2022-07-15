package com.csn.csn.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class EmailAuthServiceImplTest {

    @Autowired
    EmailAuthService emailAuthService;
    MockHttpSession mockHttpSession = new MockHttpSession();

    @Test
    @DisplayName("이메일 인증 코드 전송하기")
    void sendEmail() {
        mockHttpSession.setAttribute("email", "lalalalz@naver.com");
        emailAuthService.generateAuthCodeEmail(mockHttpSession);
    }

}