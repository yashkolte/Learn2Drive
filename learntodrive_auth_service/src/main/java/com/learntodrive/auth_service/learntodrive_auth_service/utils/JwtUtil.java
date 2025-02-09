package com.learntodrive.auth_service.learntodrive_auth_service.utils;

import com.learntodrive.auth_service.learntodrive_auth_service.role.Role;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Set;

@Component
public class JwtUtil {
    private final Key signingKey;
    private final long expirationTime;

    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.expiration}") long expirationTime) {
        // Use consistent Base64 encoding
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        this.signingKey = Keys.hmacShaKeyFor(keyBytes);
        this.expirationTime = expirationTime;
    }

    public String generateToken(String email, Set<Role> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
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
