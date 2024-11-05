package use_case.transaction.one_time.shared_account;

import use_case.transaction.one_time.OneTimeTransactionOutputBoundary;

/**
 * The SharedAccountOneTimeTransactionOutputBoundary interface provides methods for preparing the view based on the outcome
 * of a one-time transaction operation that involves a shared account.
 * Implementations of this interface will handle the presentation logic for successful and failed one-time transaction
 * attempts for shared accounts.
 *
 * @author Rita
 * @author Eric
 */
public interface SharedAccountOneTimeTransactionOutputBoundary extends OneTimeTransactionOutputBoundary<SharedAccountOneTimeTransactionOutputData> {
}