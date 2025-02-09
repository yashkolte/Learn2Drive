package com.learntodrive.auth_service.learntodrive_auth_service.controller;

import com.learntodrive.auth_service.learntodrive_auth_service.model.User;
import com.learntodrive.auth_service.learntodrive_auth_service.role.Role;
import com.learntodrive.auth_service.learntodrive_auth_service.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/user/register")
    public Map<String, String> userregister(@RequestBody User user) {
        user.setRoles(Collections.singleton(Role.USER)); // Default role as USER
        String message = authService.register(user);
        return Collections.singletonMap("message", message);
    }

    @PostMapping("/driver/register")
    public Map<String, String> driverregister(@RequestBody User user) {
        user.setRoles(Collections.singleton(Role.DRIVER)); // Default role as USER
        String message = authService.register(user);
        return Collections.singletonMap("message", message);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        System.out.println(credentials);
        String token = authService.login(credentials.get("email"), credentials.get("password"));
        return Collections.singletonMap("token", token);
    }

    @PostMapping("/submit-details")
    public ResponseEntity<?> submitUserDetails(@RequestHeader("Authorization") String token,
                                               @RequestBody Map<String, String> userDetails) {
        if (!authService.validateToken(token)) {
            return ResponseEntity.status(403).body("Invalid Token!");
        }

        String response = authService.sendUserDetailsToService(token,
                userDetails.get("dob"),
                userDetails.get("aadharNo"),
                userDetails.get("licenceNo"));
        return ResponseEntity.ok(response);
    }
}