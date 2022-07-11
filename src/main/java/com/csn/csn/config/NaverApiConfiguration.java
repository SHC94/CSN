package com.csn.csn.config;

import com.csn.csn.comm.NaverApiCall;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NaverApiConfiguration {
    @Bean
    public NaverApiCall bookService() {
        return new NaverApiCall();
    }
}
