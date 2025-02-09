package com.learntodrive.user_detail.learntodrive_user_detail.controller;

import com.learntodrive.user_detail.learntodrive_user_detail.model.UserDetails;
import com.learntodrive.user_detail.learntodrive_user_detail.repo.UserDetailsRepository;
import com.learntodrive.user_detail.learntodrive_user_detail.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-details")
public class UserDetailsController {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    public UserDetailsController(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUserDetails(@RequestBody UserDetails userDetails, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(403).body("Missing or invalid Authorization header!");
        }

        // Clean the token by removing "Bearer " and trimming any spaces
        String token = authHeader.substring(7).trim();

        // Add debug logging
        System.out.println("Processing token: " + token.substring(0, Math.min(10, token.length())) + "...");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(403).body("Invalid Token!");
        }

        try {
            String email = jwtUtil.extractEmail(token);
            userDetails.setUserEmail(email);
            userDetailsRepository.save(userDetails);
            return ResponseEntity.ok("User details saved successfully!");
        } catch (Exception e) {
            System.out.println("Error processing token: " + e.getMessage());
            return ResponseEntity.status(500).body("Error processing token: " + e.getMessage());
        }
    }
}