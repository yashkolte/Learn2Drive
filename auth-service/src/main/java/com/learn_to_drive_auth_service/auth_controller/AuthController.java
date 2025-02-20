package com.learn_to_drive_auth_service.auth_controller;

import org.springframework.web.bind.annotation.*;

import com.learn_to_drive_auth_service.config.SecurityConfig;
import com.learn_to_drive_auth_service.model.User;
import com.learn_to_drive_auth_service.services.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@CrossOrigin(origins = "*") // Allow all origins
@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);
	
	
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 🔹 Register a new user
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        System.out.println("Entering Register");
        return ResponseEntity.ok(authService.registerUser(user));
    }

    // 🔹 User Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
    	 log.debug("🟢 Received login request for: " + request.get("email"));
        String token = authService.loginUser(request.get("email"), request.get("password"));
        log.debug("🔐 Authentication successful for: " + request.get("email"));
        return ResponseEntity.ok(token);
    }
}
