package com.example.expenseTracker.application.services;

import com.example.expenseTracker.adaptors.security.JwtService;

import com.example.expenseTracker.application.services.auth.AuthService;
import com.example.expenseTracker.application.use_case_ports.transaction.TransactionUseCase;
import com.example.expenseTracker.domain.entity.account.user_acc.UserAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PeriodicTxUpdater {
    private final TransactionUseCase txUseCase;
    private final JwtService jwt;          // or whatever issues tokens

    /** Run every login to materialise any missed periodic events. */
    public void updateForUserSinceLastLogin(UserAccount user) {
        txUseCase.materialiseDue(user.getId());
    }

    /** Issue a new access / refresh token pair (JWT). */
    public AuthService.TokenPair issueTokens(UserAccount user) {
        String access  = jwt.generateAccessToken (user.getId(), user.getUsername());
        String refresh = jwt.generateRefreshToken(user.getId(), user.getUsername());
        return new AuthService.TokenPair(access, refresh);
    }
}
