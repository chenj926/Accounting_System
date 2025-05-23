package com.example.expenseTracker.application.use_case_ports.auth;

import com.example.expenseTracker.adaptors.web.dto.LoginRequestDto;
import com.example.expenseTracker.adaptors.web.dto.SignupRequestDto;
import com.example.expenseTracker.application.services.auth.AuthService;

public interface AuthUseCase {
    AuthService.TokenPair signUp(SignupRequestDto dto);
    AuthService.TokenPair login(LoginRequestDto dto);
}
