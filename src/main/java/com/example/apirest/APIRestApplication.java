package com.example.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class APIRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(APIRestApplication.class, args);
	}

}
