package com.example.expenseTracker.application.ports.auth;


public interface JwtServiceRepository {
    String generateAccessToken(Long userId, String username);
    String generateRefreshToken(Long userId, String username);
    boolean isValid(String token);
    String extractUsername(String token);        // handy later
}