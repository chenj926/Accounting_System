package com.example.expenseTracker.application.services;

import com.example.expenseTracker.adaptors.security.JwtService;
import com.example.expenseTracker.application.ports.transaction.periodic.PeriodicTransactionPort;
import com.example.expenseTracker.domain.entity.account.user_acc.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class PeriodicTxUpdater {
    private final PeriodicTransactionPort txPort;
    private final JwtService jwt;          // or whatever issues tokens

    public PeriodicTxUpdater(PeriodicTransactionPort txPort, JwtService jwt) {
        this.txPort = txPort;
        this.jwt = jwt;
    }

    // update to backend 定期 update latter
    public void updateForUserSinceLastLogin(UserAccount userAccount) {
        txPort.createMissingTransactionsForUser(userAccount.getId(), userAccount.getLastLoginAt());
    }

    public AuthService.TokenPair issueTokens(UserAccount u) {
        // access token
        String at  = jwt.generateAccessToken (u.getId(), u.getUsername());
        // refresh token
        String rft = jwt.generateRefreshToken(u.getId(), u.getUsername());

        return new AuthService.TokenPair(at, rft);
    }
}
