package com.example.userprofileservice.configure;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Match all API endpoints
                .allowedOrigins("http://localhost:4200")  // Allow requests from your Angular frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific HTTP methods
                .allowedHeaders("*")  // Allow any headers
                .allowCredentials(true);  // Allow sending cookies and credentials
    }
}

