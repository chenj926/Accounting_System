package com.example.expenseTracker.domain.entity.transaction;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
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
    protected Long id;
    protected Long accountId;
    protected BigDecimal amount;
    protected Instant createTime;
    protected String description;

    // Money direction
    protected Txtype txtype;
    protected String transactionCategory;

    public enum Txtype {
        INFLOW,
        OUTFLOW
    }


    protected Transaction(Long id, Long accountId, BigDecimal amount,
                          String description, Instant createTime, Txtype txtype,
                          String transactionCategory) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
        this.createTime = createTime;
        this.txtype = txtype;
        this.transactionCategory = transactionCategory;
    }

}
