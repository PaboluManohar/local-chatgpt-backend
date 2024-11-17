package com.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Allow CORS for API endpoints
                .allowedOrigins("*") // Allow all origins (no restrictions)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow all methods
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow sending cookies (if needed)
    }
}
