package com.example.userprofileservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
@OpenAPIDefinition(info = @Info(title ="StocklistingApp UserProfile Service", version = "1.0",description = "Stock listing Application"))
public class UserProfileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProfileServiceApplication.class, args);
	}

}
