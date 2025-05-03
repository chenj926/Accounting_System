package com.example.expenseTracker.domain.entity.transaction;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * The Transaction class provides a blueprint for a financial transaction entity.
 * It includes transaction details such as date, identification, description, and amount.
 *
 * Abstract since specific transaction types may extend it.
 *
 * @author Jessica
 * @author Eric
 */

@Getter
@Setter
public abstract class Transaction {
    protected String id;
    protected float amount;
    protected LocalDate date;
    protected String description;
    protected boolean inflow;
    protected String transactionCategory;

    public void setAmount(float amount) {
        this.amount = amount;
        this.inflow = amount >= 0;
    }
}
