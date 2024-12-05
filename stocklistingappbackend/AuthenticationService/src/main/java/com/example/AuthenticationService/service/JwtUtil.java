package com.example.AuthenticationService.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private String secretKey = "mySuperSecretKey123";  // Replace with a secure secret key
    private long expirationTime = 60 * 60 * 1000;  // 1 hour in milliseconds

    // Method to generate a JWT token
    public String generateToken(String username, Long userId) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("userId", userId); // Adding userId to the claims

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Method to validate a JWT token
    public boolean isValidToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            // Check if the token is expired
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false; // Return false if the token is invalid
        }
    }

    // Method to extract the username from the token
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
