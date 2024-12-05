package com.example.AuthenticationService.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public FilterRegistrationBean<JWTFilters> jwtFilter() {
        FilterRegistrationBean<JWTFilters> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JWTFilters());
        filterRegistrationBean.addUrlPatterns("/protected"); // Specify the URL patterns to apply the filter
        return filterRegistrationBean;
    }
}
