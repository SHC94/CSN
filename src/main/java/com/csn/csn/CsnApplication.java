package com.csn.csn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class CsnApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsnApplication.class, args);
	}

}
