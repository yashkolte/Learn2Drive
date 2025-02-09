package com.learntodrive.auth_service.learntodrive_auth_service.services;

import com.learntodrive.auth_service.learntodrive_auth_service.model.User;
import com.learntodrive.auth_service.learntodrive_auth_service.repo.UserRepository;
import com.learntodrive.auth_service.learntodrive_auth_service.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final RestTemplate restTemplate = new RestTemplate();

    public String register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "Registered successfully!";
    }

    public String login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return jwtUtil.generateToken(user.getEmail(), user.getRoles());
            }
        }
        return "Invalid credentials!";
    }

    public String sendUserDetailsToService(String jwtToken, String dob, String aadharNo, String licenceNo) {
        String userDetailsServiceUrl = "http://localhost:8082/user-details/save"; // Ensure correct port

        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("dob", dob);
        userDetails.put("aadharNo", aadharNo);
        userDetails.put("licenceNo", licenceNo);

        // ✅ Properly include JWT in request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(jwtToken); // ✅ Proper way to set JWT Token

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(userDetails, headers);

        // ✅ Handle response properly
        try {
            ResponseEntity<String> response = restTemplate.exchange(userDetailsServiceUrl, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();
        } catch (Exception e) {
            return "Failed to send user details: " + e.getMessage();
        }
    }
}
