package use_case.transaction.periodic.shared_account;

import use_case.transaction.periodic.PeriodicTransactionOutputBoundary;

/**
 * The {@code SharedAccountPeriodicTransactionOutputBoundary} interface provides methods for preparing view data
 * based on the outcome of a periodic transaction operation involving a shared account.
 * <p>
 * This interface extends {@link PeriodicTransactionOutputBoundary} and specifies the output data type for
 * shared account periodic transactions. Implementations of this interface are responsible for presenting the
 * results of periodic transaction operations, including both successful and failed outcomes.
 * </p>
 *
 * @see PeriodicTransactionOutputBoundary
 * @see SharedAccountPeriodicTransactionOutputData
 *
 * @author Rita
 */
public interface SharedAccountPeriodicTransactionOutputBoundary extends PeriodicTransactionOutputBoundary<SharedAccountPeriodicTransactionOutputData> {
}