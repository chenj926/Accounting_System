package com.example.expenseTracker.domain.entity.account.shared_acc;

import com.example.expenseTracker.domain.entity.account.Account;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

/**
 * Represents a shared account accessible by multiple users.
 * Extends base Account and manages associated user identifications.
 *
 * Authors: Rita, Jessica, Eric
 */
@Getter
@Setter
public class SharedAccount extends Account {
    private Set<String> sharedUserIdentifications;

    /**
     * Constructs a SharedAccount with shared user IDs and password.
     */
    public SharedAccount(Long sharedId, String shareAccountUsername, Set<String> userIds, String sharedAccountPassword,
                         String email, Instant lastLoginAt) {
        super(shareAccountUsername, sharedAccountPassword, sharedId, email, lastLoginAt);
        this.sharedUserIdentifications = userIds;
    }

//    /**
//     * Constructs a SharedAccount with full financial data and user IDs.
//     */
//    public SharedAccount(Long shareAccountId, String shareAccountUsername, Set<String> userIds, String sharedAccountPassword, String email,
//                         Instant lastLoginAt, float totalIncome, float totalOutflow, float totalBalance) {
//        super(shareAccountUsername, sharedAccountPassword, shareAccountId, email, lastLoginAt,
//                totalIncome, totalOutflow, totalBalance);
//        this.sharedUserIdentifications = userIds;
//    }

    /**
     * Adds a user ID to the shared user set.
     */
    public void addUserIdentification(String id) {
        sharedUserIdentifications.add(id);
    }

    /**
     * Removes a user ID from the shared user set.
     */
    public void removeUserIdentification(String id) {
        sharedUserIdentifications.remove(id);
    }
}
