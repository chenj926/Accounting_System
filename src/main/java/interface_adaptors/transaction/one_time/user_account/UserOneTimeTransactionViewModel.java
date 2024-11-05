package interface_adaptors.transaction.one_time.user_account;

import interface_adaptors.transaction.one_time.OneTimeTransactionViewModel;

/**
 * The {@code UserOneTimeTransactionViewModel} class extends {@code OneTimeTransactionViewModel}
 * and manages the state and labels specific to the one-time transaction view for user accounts.
 * It provides access to various labels used in the one-time transaction view and supports
 * property change notifications to ensure that the view is updated when the state changes.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the user interface reflects the current state of one-time transactions for user accounts.
 * </p>
 *
 * <p>
 * <b>Author:</b> Eric Chen
 * </p>
 */
public class UserOneTimeTransactionViewModel extends OneTimeTransactionViewModel<
        UserOneTimeTransactionState> {

    /**
     * Constructs a {@code UserOneTimeTransactionViewModel} object with the view name set to "One Time Transaction".
     * Initializes the state to a new {@code UserOneTimeTransactionState} instance.
     */
    public UserOneTimeTransactionViewModel() {
        super("One Time Transaction");
        this.state = new UserOneTimeTransactionState();
    }

    /**
     * Resets the one-time transaction state to default values.
     * This method replaces the current state with a new {@code UserOneTimeTransactionState} instance.
     */
    @Override
    public void resetState() {
        setState(new UserOneTimeTransactionState());
    }

}

