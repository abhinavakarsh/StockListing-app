package com.example.ApiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;


@Configuration
public class AppConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/api/users/**")
                        .uri("lb://UserProfileService"))
                .route(p -> p
                        .path("/api/auth/**")
                        .uri("lb://AuthenticationService"))
                .route(p -> p
                        .path("/wishlist/**")
                        .uri("lb://WhishlistService"))
                .route(p -> p
                        .path("/stocks/**")
                        .uri("lb://StockListing"))

                        .build();

    }


}