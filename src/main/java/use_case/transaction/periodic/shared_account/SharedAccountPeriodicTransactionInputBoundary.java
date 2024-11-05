package use_case.transaction.periodic.shared_account;

import use_case.transaction.periodic.PeriodicTransactionInputBoundary;

/**
 * The {@code SharedAccountPeriodicTransactionInputBoundary} interface provides a method for executing periodic transaction operations
 * that involve a shared account.
 * <p>
 * Implementations of this interface will handle the process of creating a periodic transaction using the provided input data.
 * The input data is encapsulated in the {@link SharedAccountPeriodicTransactionInputData} class, which includes details specific to shared accounts.
 * </p>
 *
 * @see SharedAccountPeriodicTransactionInputData
 *
 * @author Rita
 * @author Jessica
 */
public interface SharedAccountPeriodicTransactionInputBoundary extends PeriodicTransactionInputBoundary<SharedAccountPeriodicTransactionInputData> {
}