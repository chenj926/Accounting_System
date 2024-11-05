package interface_adaptors.transaction.one_time.shared_account;

import interface_adaptors.transaction.one_time.OneTimeTransactionState;

/**
 * The {@code SharedOneTimeTransactionState} class manages the state specific to
 * one-time transactions within a shared account. It extends the {@code OneTimeTransactionState}
 * class and is responsible for encapsulating transaction-related data, including user IDs
 * responsible for the transaction.
 * <p>
 * This class is part of the view model state management in the Clean Architecture,
 * ensuring that the state of shared account one-time transactions is maintained and can
 * be easily accessed and modified by other components in the application.
 * </p>
 *
 * <p>
 * <b>Author:</b> Eric Chen
 * </p>
 */
public class SharedAccountOneTimeTransactionState extends OneTimeTransactionState {

    /**
     * Constructs a {@code SharedOneTimeTransactionState} with default values.
     */
    public SharedAccountOneTimeTransactionState() {
        super();
    }
}

