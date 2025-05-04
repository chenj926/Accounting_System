package com.example.expenseTracker.domain.entity.transaction.periodic;

import com.example.expenseTracker.domain.entity.transaction.Transaction;
import com.example.expenseTracker.domain.entity.transaction.onetime.OneTimeTransaction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

/**
 * Represents a recurring financial transaction with start/end dates and a recurrence period.
 * Inherits basic transaction properties from the Transaction class.
 *
 * Authors: Xile, Chi Fong, Dana, Jessica, Eric
 */
@Getter
@Setter
public class PeriodicTransaction extends Transaction {

    private Duration interval;
    private Instant lastExecutedAt;

    /**
     * Constructs a PeriodicTransaction with specified attributes.
     */
    public PeriodicTransaction(Long id, Long accountId, BigDecimal amount,
                               String description, Instant createTime, Txtype txtype,
                               String transactionCategory,
                               Duration interval, Instant lastExecutedAt) {
        super(id, accountId, amount, description, createTime, txtype, transactionCategory);
        this.interval = interval;
        this. lastExecutedAt = lastExecutedAt;
    }

    /**
     * Returns true if a new instance should be generated now
     *
     * A transaction is considered due if:
     * - It has never been run before (`lastRun == null`), or
     * - The time since the last run is greater than or equal to the interval.
     *
     * @param now The current timestamp to compare against the last run time.
     * @return true if the transaction should run again; false otherwise.
     */
    public boolean isExecutionDue(Instant now) {
        return this.lastExecutedAt == null
                || this.lastExecutedAt.plus(this.interval).isBefore(now);
    }

    /** Mark this rule as executed at <code>now</code>. */
    public void markExecuted(Instant now) {
        this.lastExecutedAt = now;
    }

    /** Factory that materialises the cash event. */
    public OneTimeTransaction createInstance(Instant executionTime) {
        return new OneTimeTransaction(
                null, getAccountId(), getAmount(), getDescription(),
                executionTime, getTxtype(), getTransactionCategory());
    }
}
