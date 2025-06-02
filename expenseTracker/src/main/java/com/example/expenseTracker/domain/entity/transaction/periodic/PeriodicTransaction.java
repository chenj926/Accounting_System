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

    private Duration interval;  // e.g., PT30D for “every 30 days”
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
        this.lastExecutedAt = lastExecutedAt;
    }

    /**
     * Returns true if a new instance should be generated now.
     * A transaction is considered due if its calculated next execution time is not after the current time 'now'.
     *
     * @param now The current timestamp to compare against.
     * @return true if the transaction should run again; false otherwise.
     */
    public boolean isExecutionDue(Instant now) {
        Instant nextDue = nextExecutionTime();

        // If nextExecutionTime() returned null (e.g., due to missing critical data for a first run),
        // then it cannot be determined if it's due, so treat as not due.
        if (nextDue == null) {
            return false;
        }

        // The rule is due if the next calculated execution time is before or exactly at 'now'.
        // !nextDue.isAfter(now) is equivalent to nextDue <= now
        return !nextDue.isAfter(now);
//        return this.lastExecutedAt == null
//                || this.lastExecutedAt.plus(this.interval).isBefore(now);
    }

    /**
     * Calculates the next execution time for this periodic rule.
     * Handles cases where lastExecutedAt might be null (e.g., for the very first execution).
     *
     * @return The Instant of the next execution, or null if critical data (like createTime or interval) is missing for a first run.
     */
    public Instant nextExecutionTime() {
        if (this.lastExecutedAt == null) {
            // This rule was likely fetched because its last_executed_at in the database was NULL,
            // indicating it has never been executed or its initial state was set to NULL.
            // The first execution time is typically based on the rule's creation time plus its interval,
            // or a specific firstTxTime defined during scheduling.
            // The 'createTime' field (rule's own creation timestamp) is available.
            // Using createTime + interval is a reasonable fallback if lastExecutedAt is null.
            // A more robust system ensures 'lastExecutedAt' is always initialized to a meaningful
            // value (e.g., 'firstTxTime.minus(interval)' or 'ruleCreateTime' if no specific firstTxTime)
            // rather than allowing database NULLs to propagate to this logic directly without context.

            if (this.getCreateTime() == null || this.interval == null) {
                // Defensive coding: if createTime (rule's own creation) or interval is also null,
                // we cannot reliably calculate the next execution time.
                // This would indicate a deeper data integrity issue or an improperly initialized rule.
                // Logging an error is advisable in a real application.
                System.err.println("Error: PeriodicRule ID " + getId() +
                        " has null lastExecutedAt and also null createTime or interval." +
                        " Cannot determine next execution time.");
                return null; // Cannot determine next execution; an error should be logged.
            }
            // Assumption: if lastExecutedAt is null, the "next" is relative to the rule's own creation time.
            return this.getCreateTime().plus(this.interval);
        }
        // If lastExecutedAt is not null, the next execution is simply lastExecutedAt + interval.
        return this.lastExecutedAt.plus(this.interval);
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
