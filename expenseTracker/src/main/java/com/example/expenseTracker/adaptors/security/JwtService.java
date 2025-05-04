package com.example.expenseTracker.adaptors.security;

import com.example.expenseTracker.application.ports.auth.JwtServiceRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;


@Component
public class JwtService implements JwtServiceRepository {
    private final SecretKey key;
    private final long accessTtlMillis;  // accessToken TimeToLiveIn Milliseconds
    private final long refreshTtlMillis;

    public JwtService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-ttl}") long accessTtlMillis,
            @Value("${jwt.refresh-ttl}") long refreshTtlMillis) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTtlMillis = accessTtlMillis;
        this.refreshTtlMillis = refreshTtlMillis;
    }

    @Override
    public String generateAccessToken(Long id, String username) {
        return buildToken(id, username, accessTtlMillis);
    }

    @Override
    public String generateRefreshToken(Long id, String username) {
        return buildToken(id, username, refreshTtlMillis);
    }

    @Override
    public boolean isValid(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    @Override
    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    /* helper */
    private String buildToken(Long userId, String username, long ttl) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(username)
                .claim("uid", userId)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(ttl)))
                .signWith(key)
                .compact();
    }
}
