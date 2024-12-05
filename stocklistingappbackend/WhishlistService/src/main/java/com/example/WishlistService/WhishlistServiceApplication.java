package com.example.WishlistService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title ="wishlist Service", version = "1.0",description = "food nutrition track Application"))
public class WhishlistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhishlistServiceApplication.class, args);
	}

}
