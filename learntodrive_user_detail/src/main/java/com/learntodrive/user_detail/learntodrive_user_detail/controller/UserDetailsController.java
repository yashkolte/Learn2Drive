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
    private final JwtUtil jwtUtil; // ✅ Ensure it's properly injected

    @Autowired
    public UserDetailsController(UserDetailsRepository userDetailsRepository, JwtUtil jwtUtil) {
        this.userDetailsRepository = userDetailsRepository;
        this.jwtUtil = jwtUtil; // ✅ Properly initialize the JwtUtil object
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUserDetails(@RequestBody UserDetails userDetails, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(403).body("Missing or invalid Authorization header!");
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(403).body("Invalid Token!");
        }

        String email = jwtUtil.extractEmail(token);
        userDetails.setUserEmail(email);

        userDetailsRepository.save(userDetails);
        return ResponseEntity.ok("User details saved successfully!");
    }
}
