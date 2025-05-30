package com.restapi.newsapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Value("${cors.origin}")
    private String origin;

    @Override
    public void addCorsMappings(
            org.springframework.web.servlet.config.annotation.CorsRegistry registry
    ) {
        registry
                .addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedOrigins(origin)
                .allowedHeaders("*");
    }
}
