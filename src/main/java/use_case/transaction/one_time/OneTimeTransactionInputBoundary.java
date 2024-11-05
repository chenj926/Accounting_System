package use_case.transaction.one_time;

import use_case.transaction.TransactionInputBoundary;
import use_case.transaction.TransactionInputData;

/**
 * The {@code OneTimeTransactionInputBoundary} interface defines the boundary for executing one-time transaction operations.
 * <p>
 * Implementations of this interface will handle the process of creating a one-time transaction using the provided input data.
 * The input data is encapsulated in a class that extends {@link TransactionInputData}.
 * </p>
 *
 * @param <I> the type of input data used for one-time transaction operations
 *
 * @see TransactionInputBoundary
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
public interface OneTimeTransactionInputBoundary<I> extends TransactionInputBoundary<I> {
}
