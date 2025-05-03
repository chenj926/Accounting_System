package com.example.expenseTracker.domain.entity.account;

import com.example.expenseTracker.domain.entity.transaction.Transaction;
import com.example.expenseTracker.domain.entity.transaction.TransactionComparator;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user account containing login credentials and financial transactions.
 * Provides methods for managing transactions and tracking balances.
 *
 * @author Jessica
 * @author Eric
 */
@Setter
@Getter
public abstract class Account {
    /** Username for the account */
    protected String username;

    /** Password for the account */
    protected String password;

    /** Unique identifier for the account */
    protected Long id;

    protected String email;

    /** List of financial transactions */
    protected List<Transaction> transactions;

    /** Total income recorded */
    protected float totalIncome;

    /** Total outflow recorded */
    protected float totalOutflow;

    /** Net balance */
    protected float totalBalance;

    /** Last login date */
    protected Instant lastLoginAt;

    /**
     * Constructs an Account with default balances and empty transaction list.
     */
    public Account(String username, String password, Long id, String email,
                   Instant lastLoginAt) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
        this.transactions = new ArrayList<>();
        this.totalIncome = 0.0f;
        this.totalOutflow = 0.0f;
        this.totalBalance = 0.0f;
        this.lastLoginAt = lastLoginAt;
    }

    /**
     * Constructs an Account with specified balances.
     */
    public Account(String username, String password, Long id, String email, Instant lastLoginAt,
                   float totalIncome, float totalOutflow, float totalBalance) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.email = email;
        this.transactions = new ArrayList<>();
        this.totalIncome = totalIncome;
        this.totalOutflow = totalOutflow;
        this.totalBalance = totalBalance;
        this.lastLoginAt = lastLoginAt;
    }

    /**
     * Returns a copy of the transaction list.
     */
    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    /**
     * Adds a transaction and updates totals.
     */
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        updateTotals(transaction);
        transactions.sort(new TransactionComparator());
    }

    /**
     * Updates totals based on a transaction.
     */
    private void updateTotals(Transaction transaction) {
        float amount = transaction.getAmount();
        if (amount > 0) {
            totalIncome += amount;
        } else {
            totalOutflow += amount;
        }
        totalBalance += amount;
    }
}
