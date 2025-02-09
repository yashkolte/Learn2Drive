package com.learntodrive.user_detail.learntodrive_user_detail.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;

@Component
public class JwtUtil {
    private final Key signingKey;

    public JwtUtil(@Value("${jwt.secret}") String secret) {
        // Use consistent Base64 encoding
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
    }


    public String extractEmail(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Error extracting email from token", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            // Trim any potential spaces
            String cleanToken = token.trim();
            Jwts.parserBuilder()
                    .setSigningKey(signingKey)
                    .build()
                    .parseClaimsJws(cleanToken);
            return true;
        } catch (JwtException e) {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }
}

