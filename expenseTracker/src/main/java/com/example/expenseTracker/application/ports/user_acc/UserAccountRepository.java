package com.example.expenseTracker.application.ports.user_acc;

import com.example.expenseTracker.domain.entity.account.user_acc.UserAccount;

import java.util.Optional;

public interface UserAccountRepository {
    Optional<UserAccount> findByUsername(String username);
    Optional<UserAccount> findByEmail(String email);
//    boolean checkPassword(String password);
    UserAccount saveUser(UserAccount user);
    void updateLastLogin(Long userId, java.time.Instant ts);
}
