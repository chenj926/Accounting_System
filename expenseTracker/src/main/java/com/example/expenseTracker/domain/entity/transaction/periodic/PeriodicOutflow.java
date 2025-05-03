package com.example.expenseTracker.domain.entity.transaction.periodic;

import java.time.LocalDate;

/**
 * The PeriodicOutflow class represents a recurring outflow transaction.
 * It extends the PeriodicTransaction class.
 *
 * @author Chi Fong
 * @author Jessica
 * @author Eric
 */
public class PeriodicOutflow extends PeriodicTransaction {

    /**
     * Constructs a PeriodicOutflow object with the specified details.
     *
     * @param id             the id of the transaction
     * @param amount         the amount of the transaction
     * @param startDate      the start date of the transaction
     * @param description    the description of the transaction
     * @param endDate        the end date of the transaction
     * @param period         the period of the transaction in days
     */
    public PeriodicOutflow(String id, float amount, LocalDate startDate, String description,
                           LocalDate endDate, String period, String category) {
        super(id, amount, startDate, description, endDate, period, category);
    }
}