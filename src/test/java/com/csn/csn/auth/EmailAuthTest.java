package com.csn.csn.auth;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class EmailAuthTest {

    @Test
    @DisplayName("새롭게 생성되는지 확인한다.")
    void isNewCode() {
        // given
        EmailAuth emailAuth = new EmailAuth();
        String authCode = emailAuth.getAuthCode();

        // when
        emailAuth.nextCode();
        String newAuthCode = emailAuth.getAuthCode();

        //then
        log.info("old authCode = {}", authCode);
        log.info("new authCode = {}", newAuthCode);
        assertThat(authCode).isNotEqualTo(newAuthCode);
    }

    @Test
    @DisplayName("만료 테스트")
    void expired() {
        // given
        EmailAuth emailAuth = new EmailAuth();

        // when
        LocalDateTime target = LocalDateTime.now()
                .minusSeconds(10L);

        //then
        log.info("만료기준시간 = {}", emailAuth.getExpiredTime());
        log.info("주어진 시간 = {}", target);
        assertThat(emailAuth.isExpired(target)).isTrue();
    }

    @Test
    @DisplayName("만료 안됨 테스트")
    void nonExpired() {
        // given
        EmailAuth emailAuth = new EmailAuth();

        // when
        LocalDateTime target = LocalDateTime.now()
                .plusSeconds(10L);

        //then
        log.info("만료기준시간 = {}", emailAuth.getExpiredTime());
        log.info("주어진 시간 = {}", target);
        assertThat(emailAuth.isExpired(target)).isFalse();
    }

    @Test
    @DisplayName("인증 성공")
    void certified_ok() {
        // given
        EmailAuth emailAuth = new EmailAuth();

        // when
        String authCode = emailAuth.getAuthCode();
        LocalDateTime arrivalTime = LocalDateTime.now()
                .plusSeconds(10L);

        //then
        log.info("만료기준시간 = {}", emailAuth.getExpiredTime());
        log.info("도착 시간 = {}", arrivalTime);
        assertThat(emailAuth.isCertified(arrivalTime, authCode)).isTrue();
    }
}