package com.example.expenseTracker.domain.entity.account.shared_acc;

import com.example.expenseTracker.domain.entity.account.Account;
import lombok.Getter;
import lombok.Setter;

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
    public SharedAccount(String shareAccountIdentification, Set<String> userIds, String sharedAccountPassword) {
        super(null, sharedAccountPassword, shareAccountIdentification);
        this.sharedUserIdentifications = userIds;
    }

    /**
     * Constructs a SharedAccount with full financial data and user IDs.
     */
    public SharedAccount(String shareAccountIdentification, Set<String> userIds, String sharedAccountPassword,
                         float totalIncome, float totalOutflow, float totalBalance) {
        super(null, sharedAccountPassword, shareAccountIdentification,
                totalIncome, totalOutflow, totalBalance);
        this.sharedUserIdentifications = userIds;
    }

    /**
     * Adds a user ID to the shared user set.
     */
    public void addUserIdentification(String identification) {
        sharedUserIdentifications.add(identification);
    }

    /**
     * Removes a user ID from the shared user set.
     */
    public void removeUserIdentification(String identification) {
        sharedUserIdentifications.remove(identification);
    }
}
