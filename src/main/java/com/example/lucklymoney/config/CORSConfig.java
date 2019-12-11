package com.example.lucklymoney.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class CORSConfig extends WebMvcConfigurationSupport {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true);
        super.addCorsMappings(registry);
    }
}
