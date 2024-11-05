package use_case.transaction.periodic.user_account;

import use_case.transaction.periodic.PeriodicTransactionOutputBoundary;

/**
 * The UserAccountPeriodicTransactionOutputBoundary interface provides methods for preparing the view based on the outcome of a periodic transaction operation.
 * Implementations of this interface will handle the presentation logic for successful and failed periodic transaction attempts.
 *
 * @author Eric
 * @author Jessica
 */
public interface UserAccountPeriodicTransactionOutputBoundary extends PeriodicTransactionOutputBoundary<UserAccountPeriodicTransactionOutputData> {
}