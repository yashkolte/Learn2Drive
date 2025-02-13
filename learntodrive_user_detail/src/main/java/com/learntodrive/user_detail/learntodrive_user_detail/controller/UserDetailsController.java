package com.learntodrive.user_detail.learntodrive_user_detail.controller;

import com.learntodrive.user_detail.learntodrive_user_detail.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user-details")
public class UserDetailsController {
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/save")
    public ResponseEntity<?> saveUserDetails(@RequestHeader("Authorization") String token,
                                             @RequestBody Map<String, String> userDetails) {
        if (!token.startsWith("Bearer ")) return ResponseEntity.status(403).body("Missing Bearer token");
        if (!jwtUtil.validateToken(token.substring(7))) return ResponseEntity.status(403).body("Invalid Token!");

        return ResponseEntity.ok("User details saved successfully!");
    }
}
