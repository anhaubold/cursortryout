package com.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * CORS configuration for the application.
 * 
 * <p>Configures Cross-Origin Resource Sharing to allow the Angular frontend
 * to communicate with the backend API.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
@Configuration
public class CorsConfig {

    @Value("${spring.web.cors.allowed-origins}")
    private String allowedOrigins;

    @Value("${spring.web.cors.allowed-methods}")
    private String allowedMethods;

    @Value("${spring.web.cors.allowed-headers}")
    private String allowedHeaders;

    @Value("${spring.web.cors.allow-credentials}")
    private boolean allowCredentials;

    /**
     * Creates a CORS filter bean.
     * 
     * @return CorsFilter configured with application properties
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Set allowed origins
        if (allowedOrigins != null && !allowedOrigins.isEmpty()) {
            for (String origin : allowedOrigins.split(",")) {
                config.addAllowedOrigin(origin.trim());
            }
        }
        
        // Set allowed methods
        if (allowedMethods != null && !allowedMethods.isEmpty()) {
            for (String method : allowedMethods.split(",")) {
                config.addAllowedMethod(method.trim());
            }
        }
        
        // Set allowed headers
        if ("*".equals(allowedHeaders)) {
            config.addAllowedHeader("*");
        } else if (allowedHeaders != null && !allowedHeaders.isEmpty()) {
            for (String header : allowedHeaders.split(",")) {
                config.addAllowedHeader(header.trim());
            }
        }
        
        config.setAllowCredentials(allowCredentials);
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}


