package com.example.expenseTracker.adaptors.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    @NotBlank
    private String username;
    @Email
    private String email;
    @NotBlank
    private String password;
}
