package com.example.EcoMarket.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitimos peticiones desde los hosts locales más comunes durante desarrollo
        registry.addMapping("/**")
                // allowedOriginPatterns permite comodines y evita problemas con allowCredentials
                .allowedOriginPatterns("http://localhost:5173", "http://localhost:3000", "http://127.0.0.1:5173", "http://10.0.2.2:5173", "*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}

