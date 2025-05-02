package com.example.expenseTracker.domain.entity.transaction.periodic;

import java.time.LocalDate;

/**
 * The PeriodicInflow class represents a recurring inflow transaction.
 * It extends the PeriodicTransaction class.
 *
 * @author Chi Fong
 * @author Jessica
 * @author Eric
 */
public class PeriodicInflow extends PeriodicTransaction {
    public PeriodicInflow(String identification, float amount, LocalDate startDate, String description,
                          LocalDate endDate, String period, String category) {
        super(identification, amount, startDate, description, endDate, period, category);

    }
}
