package com.learn_to_drive_api_gateway.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import com.learn_to_drive_api_gateway.util.JwtUtil;

import reactor.core.publisher.Mono;

public class JwtAuthFilter implements WebFilter {

    private final JwtUtil jwtUtil;

    // Constructor injection for JwtUtil
    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // Bypass Authentication for Auth Service (Login/Signup)
        if (request.getURI().getPath().contains("/auth/")) {
            return chain.filter(exchange);
        }

        // Check for Authorization Header
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new RuntimeException("Missing Authorization Header");
        }

        // Extract JWT token
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (token == null || !token.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Authorization Header");
        }

        token = token.substring(7); // Remove "Bearer " prefix

        try {
            // Validate token using JwtUtil
            if (!jwtUtil.validateToken(token)) {
                throw new RuntimeException("Invalid or Expired Token");
            }

            // Extract userId from token
            String userId = jwtUtil.extractUserId(token);

            // Add userId to request headers
            ServerHttpRequest modifiedRequest = exchange.getRequest().mutate()
                    .header("userId", userId)
                    .build();

            return chain.filter(exchange.mutate().request(modifiedRequest).build());

        } catch (Exception e) {
            throw new RuntimeException("Authentication Failed: " + e.getMessage());
        }
    }
}
