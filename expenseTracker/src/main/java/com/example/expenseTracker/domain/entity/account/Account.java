package com.example.expenseTracker.domain.entity.account;

import com.example.expenseTracker.domain.entity.transaction.Transaction;
import com.example.expenseTracker.domain.entity.transaction.TransactionComparator;
import lombok.Getter;
import lombok.Setter;

import javax.swing.plaf.basic.BasicIconFactory;
import java.math.BigDecimal;
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
    protected BigDecimal totalIncome;

    /** Total outflow recorded */
    protected BigDecimal totalOutflow;

    /** Net balance */
    protected BigDecimal totalBalance;

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
        this.totalIncome = BigDecimal.valueOf(0.0);
        this.totalOutflow = BigDecimal.valueOf(0.0);
        this.totalBalance = BigDecimal.valueOf(0.0);
        this.lastLoginAt = lastLoginAt;
    }

    /**
     * Constructs an Account with specified balances.
     */
    public Account(String username, String password, Long id, String email, Instant lastLoginAt,
                   BigDecimal totalIncome, BigDecimal totalOutflow, BigDecimal totalBalance) {
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
        BigDecimal amount = transaction.getAmount();
        Transaction.Txtype txtype = transaction.getTxtype();

        if (txtype == Transaction.Txtype.INFLOW) {
            this.totalIncome = totalIncome.add(amount);
        } else {
            this.totalOutflow = totalOutflow.add(amount);
        }
        this.totalBalance = totalBalance.add(amount);
    }
}
