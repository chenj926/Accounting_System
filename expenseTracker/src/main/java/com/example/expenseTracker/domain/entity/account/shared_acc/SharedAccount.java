package com.example.expenseTracker.domain.entity.account.shared_acc;


import com.example.expenseTracker.domain.entity.account.Account;

import java.util.*;

/**
 * The SharedAccount class extends the UserAccount class to represent a shared account.
 * It includes additional functionality to manage user identifications associated with the shared account.
 *
 * @author Rita
 * @author Jessica
 * @author Eric
 */
public class SharedAccount extends Account {
    private Set<String> sharedUserIdentifications;

    /**
     * Constructs a SharedAccount object with the specified shared account identification.
     * Initializes an empty set of shared user identifications.
     *
     * @param shareAccountIdentification the identification for the shared account
     */
    public SharedAccount(String shareAccountIdentification, Set<String> userIds, String sharedAccountPassword) {
        super(null, sharedAccountPassword, shareAccountIdentification); // SharedAccount have own shared account id
        this.sharedUserIdentifications = userIds;
    }

    public SharedAccount(String shareAccountIdentification, Set<String> userIds, String sharedAccountPassword,
                         float totalIncome, float totalOutflow, float totalBalance) {
        super(null, sharedAccountPassword, shareAccountIdentification,
                totalIncome, totalOutflow, totalBalance); // SharedAccount have own shared account id
        this.sharedUserIdentifications = userIds;
    }

    /**
     * Gets the set of shared user identifications associated with this shared account.
     *
     * @return a set of shared user identifications
     */
    public Set<String> getSharedUserIdentifications() {
        return this.sharedUserIdentifications;
    }

    /**
     * Adds a user identification to the set of shared user identifications.
     *
     * @param identification the user identification to be added
     */
    public void addUserIdentification(String identification) {
        sharedUserIdentifications.add(identification);
    }

    /**
     * Removes a user identification from the set of shared user identifications.
     *
     * @param identification the user identification to be removed
     */
    public void removeUserIdentification(String identification) {
        sharedUserIdentifications.remove(identification);
    }

    /**
     * Sets the shared user identifications for this shared account.
     *
     * @param sharedUserIdentifications the new set of shared user identifications
     */
    public void setSharedUserIdentifications(Set<String> sharedUserIdentifications) {
        this.sharedUserIdentifications = sharedUserIdentifications;
    }
}