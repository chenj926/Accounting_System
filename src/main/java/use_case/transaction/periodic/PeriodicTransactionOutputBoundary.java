package use_case.transaction.periodic;

import use_case.transaction.TransactionOutputBoundary;

/**
 * The PeriodicTransactionOutputBoundary interface extends the TransactionOutputBoundary
 * to define the contract for output boundaries specifically related to periodic transactions.
 * It ensures that implementations handle the output of periodic transactions in a manner consistent
 * with the provided output data type.
 *
 * @param <O> the type of the output data
 *
 * @author Eric
 */
public interface PeriodicTransactionOutputBoundary<O> extends TransactionOutputBoundary<O> {
}
