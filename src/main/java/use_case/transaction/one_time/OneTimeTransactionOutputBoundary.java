package use_case.transaction.one_time;

import use_case.transaction.TransactionOutputBoundary;

/**
 * The OneTimeTransactionOutputBoundary interface defines methods for preparing the view data based on the outcome
 * of a one-time transaction operation. Implementations of this interface will handle presenting the results
 * of one-time transactions, including both inflows and outflows.
 *
 * @param <O> The type of output data used to present the results of the one-time transaction
 *
 * @author Eric
 */
public interface OneTimeTransactionOutputBoundary<O> extends TransactionOutputBoundary<O> {
}
