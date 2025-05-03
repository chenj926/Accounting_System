package com.example.expenseTracker.adaptors.web.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String email;
    private String password;
}
