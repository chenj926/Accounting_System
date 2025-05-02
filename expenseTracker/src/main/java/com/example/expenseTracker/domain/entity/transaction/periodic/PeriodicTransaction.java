package com.example.expenseTracker.domain.entity.transaction.periodic;

import com.example.expenseTracker.domain.entity.transaction.Transaction;
import lombok.Getter;
import lombok.Setter;
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

    private LocalDate startDate;
    private LocalDate endDate;
    private String period;

    /**
     * Constructs a PeriodicTransaction with specified attributes.
     */
    public PeriodicTransaction(String identification, float amount, LocalDate startDate, String description,
                               LocalDate endDate, String period, String transactionCategory) {
        this.identification = identification;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = period;
        this.description = description;
        this.inflow = (amount >= 0);
        this.date = startDate;
        this.transactionCategory = transactionCategory;
    }
}
