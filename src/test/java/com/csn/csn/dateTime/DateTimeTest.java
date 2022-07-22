package com.csn.csn.dateTime;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class DateTimeTest {

    @Test
    void test() {
        String input = "Fri, 22 Jul 2022 11:36:00 +0900";

        DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME;

        LocalDateTime parse = LocalDateTime.parse(input, formatter);

        log.info("{}", parse);
    }
}
