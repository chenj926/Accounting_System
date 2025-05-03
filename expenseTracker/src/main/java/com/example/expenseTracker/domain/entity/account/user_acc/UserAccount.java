package com.example.expenseTracker.domain.entity.account.user_acc;

import com.example.expenseTracker.domain.entity.account.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user account with shared account functionality.
 * Inherits basic account behavior and adds ability to manage shared accounts.
 *
 * Authors: Eric, Jessica
 */
@Getter
@Setter
public class UserAccount extends Account {
    private Set<String> sharedAccounts;

    /**
     * Constructs a UserAccount with empty shared accounts and zeroed balances.
     */
    public UserAccount(String username, String password, Long userAccountId, String email,
                       Instant lastLoginAt) {
        super(username, password, userAccountId, email, lastLoginAt);
        this.sharedAccounts = new HashSet<>();
    }

    /**
     * Constructs a UserAccount with specified balances and empty shared accounts.
     */
    public UserAccount(String username, String password, Long userAccountId, String email, Instant lastLoginAt,
                       float totalIncome, float totalOutflow, float totalBalance) {
        super(username, password, userAccountId, email, lastLoginAt, totalIncome, totalOutflow, totalBalance);
        this.sharedAccounts = new HashSet<>();
    }

    /**
     * Adds a shared account ID to this user account.
     */
    public void addSharedAccount(String sharedAccountId) {
        this.sharedAccounts.add(sharedAccountId);
    }

    /**
     * Removes a shared account ID from this user account.
     */
    public void removeSharedAccount(String sharedAccountId) {
        this.sharedAccounts.remove(sharedAccountId);
    }
}
