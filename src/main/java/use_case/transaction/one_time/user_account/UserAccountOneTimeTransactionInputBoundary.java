package use_case.transaction.one_time.user_account;

import use_case.transaction.one_time.OneTimeTransactionInputBoundary;

/**
 * The OneTimeTransactionInputBoundary interface provides a method for executing one-time transaction operations.
 * Implementations of this interface will handle the process of creating a one-time transaction using the provided input data.
 *
 * @author Dana
 * @author Jessica
 * @author Eric
 */
public interface UserAccountOneTimeTransactionInputBoundary extends OneTimeTransactionInputBoundary<UserAccountOneTimeTransactionInputData> {
}