package com.example.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JavaApplication {


	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(JavaApplication.class, args);
	}

}
