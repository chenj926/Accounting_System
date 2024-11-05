package use_case.transaction.periodic;

import use_case.transaction.TransactionInputBoundary;

/**
 * Interface for handling input boundaries for periodic transactions.
 * Extends the TransactionInputBoundary interface to provide input handling
 * specific to periodic transactions.
 *
 * @param <I> the type of input data for periodic transactions
 */
public interface PeriodicTransactionInputBoundary<I> extends TransactionInputBoundary<I> {
}
