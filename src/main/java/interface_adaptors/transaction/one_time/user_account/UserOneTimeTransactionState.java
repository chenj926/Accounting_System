package interface_adaptors.transaction.one_time.user_account;

import interface_adaptors.transaction.one_time.OneTimeTransactionState;

/**
 * The {@code UserOneTimeTransactionState} class represents the state of a one-time transaction
 * specific to a user account. It includes transaction details such as the new balance,
 * transaction date, and any success or error messages associated with the transaction.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the state of one-time transactions for user accounts is maintained and can be easily accessed
 * and modified by other components in the application.
 * </p>
 *
 * <p>
 * <b>Author:</b> Eric Chen
 * </p>
 */
public class UserOneTimeTransactionState extends OneTimeTransactionState {

    /**
     * Constructs a {@code UserOneTimeTransactionState} object with default values.
     */
    public UserOneTimeTransactionState() {
        super();
    }
}
