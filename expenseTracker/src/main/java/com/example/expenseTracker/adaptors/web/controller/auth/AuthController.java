package com.example.expenseTracker.adaptors.web.controller.auth;

import com.example.expenseTracker.adaptors.web.dto.LoginRequestDto;
import com.example.expenseTracker.adaptors.web.dto.SignupRequestDto;
import com.example.expenseTracker.application.services.auth.AuthService;
import com.example.expenseTracker.application.use_case_ports.auth.AuthUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    // use the interface
    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthService.TokenPair> signup (@RequestBody SignupRequestDto dto) {
        return ResponseEntity.ok(this.authUseCase.signUp(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthService.TokenPair> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(this.authUseCase.login(dto));
    }

}
