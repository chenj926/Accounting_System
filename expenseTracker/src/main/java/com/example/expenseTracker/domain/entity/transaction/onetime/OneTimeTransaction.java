package com.example.expenseTracker.domain.entity.transaction.onetime;

import com.example.expenseTracker.domain.entity.transaction.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * The OneTimeTransaction abstract class implements the Transaction interface.
 * It represents a single, non-recurring transaction with additional details such as category and inflow status.
 *
 * @author Xile
 * @author Chi Fong
 * @author Jessica
 * @author Eric
 */
@Getter
@Setter
public class OneTimeTransaction extends Transaction {
//    private String transactionCategory;

    /**
     * Constructs a OneTimeTransaction object with the specified details.
     *
     * @param id             the identification of the transaction
     * @param amount         the amount of the transaction
     * @param date           the date of the transaction
     * @param description    the description of the transaction
     * @param category       the category of the transaction
     */
    public OneTimeTransaction(Long id, Long accountId, BigDecimal amount,
                              String description, Instant createTime, Txtype txtype,
                              String transactionCategory) {
        super(id, accountId, amount, description, createTime, txtype, transactionCategory);
    }
}
