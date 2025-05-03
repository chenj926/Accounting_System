package com.example.expenseTracker.adaptors.web.controller;

import com.example.expenseTracker.adaptors.web.dto.LoginRequestDto;
import com.example.expenseTracker.adaptors.web.dto.SignupRequestDto;
import com.example.expenseTracker.application.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthService.TokenPair> signup (@RequestBody SignupRequestDto dto) {
        return ResponseEntity.ok(this.authService.signUp(dto.getUsername(), dto.getEmail(), dto.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthService.TokenPair> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(this.authService.login(dto.getUsername(), dto.getEmail(), dto.getPassword()));
    }

}
