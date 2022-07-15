package com.csn.csn.auth;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
public class EmailAuth {

    private final int randomBound = 99999;
    private final Long effectiveTime = 180L;
    private final Random random = new Random();

    private LocalDateTime expiredTime;
    private String authCode;

    public EmailAuth() {
        expiredTime = LocalDateTime.now().plusSeconds(effectiveTime);
        authCode = String.format("%05d", random.nextInt(randomBound));
    }
 
    public void nextCode() {
        authCode = String.format("%05d", random.nextInt(randomBound));
    }

    public boolean isCertified(LocalDateTime arrivalTime, String authCode) {
        return isRightCode(authCode) && !isExpired(arrivalTime);
    }

    public boolean isRightCode(String code) {
        return authCode.equals(code);
    }
    
    public boolean isExpired(LocalDateTime arrivalTime) {
        if (arrivalTime.isAfter(expiredTime)
                || arrivalTime.isBefore(expiredTime.minusSeconds(180L))) {
            return true;
        }
        return false;
    }
}
