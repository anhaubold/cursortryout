package com.webapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for health check endpoint.
 * 
 * <p>Provides a simple health check endpoint to verify server status.</p>
 * 
 * @author Web Application Team
 * @version 1.0.0
 */
@RestController
@CrossOrigin(origins = "${spring.web.cors.allowed-origins}", allowCredentials = "true")
public class HealthController {

    /**
     * GET /health
     * Returns server status.
     * 
     * @return Health status response
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "ok");
        response.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.ok(response);
    }
}


