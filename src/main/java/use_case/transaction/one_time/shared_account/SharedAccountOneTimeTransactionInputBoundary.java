package use_case.transaction.one_time.shared_account;

import use_case.transaction.one_time.OneTimeTransactionInputBoundary;

/**
 * The SharedAccountOneTimeTransactionInputBoundary interface provides a method for executing one-time transaction operations
 * that involving the shared account.
 * Implementations of this interface will handle the process of creating a one-time transaction using the provided input data,
 * with additional logic for handling shared accounts.
 *
 * @author Rita
 * @author Jessica
 */
public interface SharedAccountOneTimeTransactionInputBoundary extends OneTimeTransactionInputBoundary<SharedAccountOneTimeTransactionInputData> {
}