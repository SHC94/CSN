package com.csn.csn.config.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.IntStream;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String source) {
        int[] intDateTime = Arrays.stream(source.split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();
        return LocalDate.of(intDateTime[0], intDateTime[1], intDateTime[2]);
    }
}
