package com.learn_to_drive_auth_service.auth_controller;

import org.springframework.web.bind.annotation.*;

import com.learn_to_drive_auth_service.model.User;
import com.learn_to_drive_auth_service.services.AuthService;

import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
    	System.out.println("Entering Register");
        return ResponseEntity.ok(authService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String token = authService.loginUser(request.get("email"), request.get("password"));
        return ResponseEntity.ok(token);
    }
}
