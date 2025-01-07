package org.thinhdev.thebankproject.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final long expirationTime;

    // Inject values from application.properties or application.yml
    public JwtUtil(@Value("${app.jwt-secret}") String jwtSecret,
                   @Value("${app.jwt-expiration}") long jwtExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)); // Generate a secure key
        this.expirationTime = jwtExpiration;
    }

    // Generate a token for the provided username
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey, SignatureAlgorithm.HS256) // Use SecretKey and specify the algorithm
                .compact();
    }

    // Extract username from the token
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey) // Use the SecretKey instance
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate the token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey) // Use SecretKey for validation
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log the exception (optional)
            return false; // Invalid token
        }
    }
}
