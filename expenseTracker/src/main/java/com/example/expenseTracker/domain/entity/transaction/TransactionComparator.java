package com.example.expenseTracker.domain.entity.transaction;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * The TransactionComparator class implements the Comparator interface for Transaction objects.
 * It provides a way to compare transactions first by date, then by amount, and finally by identification.
 *
 * @author Jessica
 */
public class TransactionComparator implements Comparator<Transaction> {

    /**
     * Compares two Transaction objects.
     * The comparison is done first by date, then by amount, and finally by identification if the dates and amounts are the same.
     *
     * @param t1 the first transaction to be compared
     * @param t2 the second transaction to be compared
     * @return a negative integer, zero, or a positive integer as the first argument is less than,
     *         equal to, or greater than the second
     */
    @Override
    public int compare(Transaction t1, Transaction t2) {
        // Compare by date
        int dateComparison = t1.getCreateTime().compareTo(t2.getCreateTime());
        if (dateComparison != 0) {
            return dateComparison;
        }

        // Compare by amount
        int amountComparison = t1.getAmount().compareTo(t2.getAmount());
        if (amountComparison != 0) {
            return amountComparison;
        }

        return t1.getId().compareTo(t2.getId());
    }
}
