package com.ssvs.seguro_salud_vida_sana.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Apply CORS to all endpoints
                .allowedOrigins("http://192.168.1.9", "https://ssvs-frontend-produccion-production.up.railway.app/")  // Allow your frontend's domain
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow credentials (if needed)
    }
}