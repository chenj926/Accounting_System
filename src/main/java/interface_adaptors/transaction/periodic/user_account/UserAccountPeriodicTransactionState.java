package interface_adaptors.transaction.periodic.user_account;

import interface_adaptors.transaction.periodic.PeriodicTransactionState;

/**
 * The {@code UserAccountPeriodicTransactionState} class represents the state of a periodic transaction
 * specific to a user account. It extends the {@code PeriodicTransactionState} class and includes
 * transaction details along with success or error messages.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the state of periodic transactions for user accounts is maintained and can be easily
 * accessed and modified by other components in the application.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen
 * </p>
 */
public class UserAccountPeriodicTransactionState extends PeriodicTransactionState {
    public UserAccountPeriodicTransactionState() {
        super();
    }

}

