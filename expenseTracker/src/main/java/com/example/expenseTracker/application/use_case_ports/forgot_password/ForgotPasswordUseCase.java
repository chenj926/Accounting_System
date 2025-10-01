package com.example.expenseTracker.application.use_case_ports.forgot_password;

import com.example.expenseTracker.adaptors.web.dto.ForgotPasswordRequestDto;
// import com.example.expenseTracker.adaptors.web.dto.ResetPasswordRequestDto; // Uncomment when you create this DTO

public interface ForgotPasswordUseCase {
    void requestPasswordReset(ForgotPasswordRequestDto dto);
    boolean validateResetToken(String token);
    // void resetPassword(ResetPasswordRequestDto dto); // Uncomment when you create this DTO
}
