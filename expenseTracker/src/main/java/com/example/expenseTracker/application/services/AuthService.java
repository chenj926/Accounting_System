package com.example.expenseTracker.application.services;

import com.example.expenseTracker.application.ports.user_acc.UserAccountRepository;
import com.example.expenseTracker.domain.entity.account.AccountFactory;
import com.example.expenseTracker.domain.entity.account.user_acc.UserAccount;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
// this is temp, we will have an abstract service for auth, and separate for useracc and sharedacc
public class AuthService {

    private final AccountFactory accountFactory;
    private final PeriodicTxUpdater periodicTxUpdater;
    private final PasswordEncoder encoder;
    private final UserAccountRepository userAccountRepository;


    public AuthService(AccountFactory accountFactory,
                       PasswordEncoder passwordEncoder,
                       UserAccountRepository userAccountRepository,
                       PeriodicTxUpdater periodicTxUpdater) {
        this.accountFactory = accountFactory;
        this.encoder = passwordEncoder;
        this.userAccountRepository = userAccountRepository;
        this.periodicTxUpdater = periodicTxUpdater;
    }

    public record TokenPair(String accessToken, String refreshToken) {
    }

    public TokenPair signUp(String password, String username, String email) {
        this.userAccountRepository.findByUsername(username).ifPresent(userAccount -> {
            throw new IllegalStateException("userId token");
        });

        // hasded password
        String hashed = encoder.encode(password);

        UserAccount saved = this.userAccountRepository.saveUser(
                this.accountFactory.createUserAccount(username, hashed, null, email, Instant.now())
        );

        // no periodic update on sign‑up
        return periodicTxUpdater.issueTokens(saved);      // e.g. JWT pair
    }

    public TokenPair login(String username, String email, String rawPassword) {
        UserAccount userAccount = null;
        if (email == null) {
            userAccount = this.userAccountRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("username incorrect!"));
        } else if (username == null) {
            userAccount = this.userAccountRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("user email incorrect!"));
        }

        if (!encoder.matches(rawPassword, userAccount.getPassword())) {
            throw new IllegalArgumentException("bad creds");
        }

        /* --- NEW: auto‑catch‑up of periodic transactions --- */
        periodicTxUpdater.updateForUserSinceLastLogin(userAccount);

        this.userAccountRepository.updateLastLogin(userAccount.getId(), Instant.now());
        return periodicTxUpdater.issueTokens(userAccount);
    }
}
