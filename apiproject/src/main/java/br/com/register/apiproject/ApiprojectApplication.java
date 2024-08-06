package br.com.register.apiproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ApiprojectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiprojectApplication.class, args);
	}
}
