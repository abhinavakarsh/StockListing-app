package com.example.StockListing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title ="StocklistingService", version = "1.0",description = "Stock listing Application"))
public class StockListingApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockListingApplication.class, args);
	}

}
