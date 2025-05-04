package com.example.expenseTracker.application.services.auth;

import com.example.expenseTracker.adaptors.web.dto.LoginRequestDto;
import com.example.expenseTracker.adaptors.web.dto.SignupRequestDto;
import com.example.expenseTracker.application.ports.user_acc.UserAccountRepository;
import com.example.expenseTracker.application.services.PeriodicTxUpdater;
import com.example.expenseTracker.application.usecase.auth.AuthUseCase;
import com.example.expenseTracker.domain.entity.account.AccountFactory;
import com.example.expenseTracker.domain.entity.account.user_acc.UserAccount;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
// this is temp, we will have an abstract service for auth, and separate for useracc and sharedacc
public class AuthService implements AuthUseCase {

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

    @Override
    public TokenPair signUp(SignupRequestDto dto) {
        this.userAccountRepository.findByUsername(dto.getUsername()).ifPresent(userAccount -> {
            throw new IllegalStateException("userId token");
        });

        // hasded password
        String hashedPwd = encoder.encode(dto.getPassword());

        UserAccount saved = this.userAccountRepository.saveUser(
                this.accountFactory.createUserAccount(dto.getUsername(), hashedPwd, null, dto.getEmail(), Instant.now())
        );

        // no periodic update on sign‑up
        return periodicTxUpdater.issueTokens(saved);      // e.g. JWT pair
    }

    @Override
    public TokenPair login(LoginRequestDto dto) {
        UserAccount userAccount = null;
        if (dto.getEmail() == null) {
            userAccount = this.userAccountRepository
                    .findByUsername(dto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("username incorrect!"));
        } else if (dto.getUsername() == null) {
            userAccount = this.userAccountRepository
                    .findByEmail(dto.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("user email incorrect!"));
        }

        if (!encoder.matches(dto.getPassword(), userAccount.getPassword())) {
            throw new IllegalArgumentException("user password incorrect!");
        }

        /* --- NEW: auto‑catch‑up of periodic transactions --- */
        periodicTxUpdater.updateForUserSinceLastLogin(userAccount);

        this.userAccountRepository.updateLastLogin(userAccount.getId(), Instant.now());
        return periodicTxUpdater.issueTokens(userAccount);
    }
}
