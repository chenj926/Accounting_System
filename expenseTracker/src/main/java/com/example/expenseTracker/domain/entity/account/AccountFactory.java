package com.example.expenseTracker.domain.entity.account;

import com.example.expenseTracker.domain.entity.account.shared_acc.SharedAccount;
import com.example.expenseTracker.domain.entity.account.user_acc.UserAccount;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;

/**
 * The AccountFactory class is responsible for creating different types of account objects.
 * It provides methods to create user accounts and shared accounts.
 *
 * @author Jessica
 */
@Component
public class AccountFactory {

    /**
     * Creates a new UserAccount object with the specified username, password, and id.
     *
     * @param username       the username for the new user account
     * @param password       the password for the new user account
     * @param id the id for the new user account
     * @return a new UserAccount object
     */
    public UserAccount createUserAccount(String username, String password, Long id, String email,
                                         Instant lastLoginAt) {
        return new UserAccount(username, password, id, email, lastLoginAt);
    }

    /**
     * Creates a new SharedAccount object with the specified id.
     *
     * @param sharedId the id for the new shared account
     * @param sharedAccountPassword the password for the new shared account
     * @return a new SharedAccount object
     */
    public SharedAccount createSharedAccount(Long sharedId, String sharedUsername, Set<String> users,
                                             String sharedAccountPassword, String email, Instant lastLoginAt) {
        return new SharedAccount(sharedId, sharedUsername, users, sharedAccountPassword, email, lastLoginAt);
    }
}