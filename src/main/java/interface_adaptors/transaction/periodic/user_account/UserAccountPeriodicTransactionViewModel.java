package interface_adaptors.transaction.periodic.user_account;

import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;

/**
 * The {@code UserAccountPeriodicTransactionViewModel} class is responsible for managing the state and labels
 * for the periodic transaction view in a user account context. This class adheres to the principles of Clean
 * Architecture by separating the concerns of the UI and the underlying business logic. It extends the
 * {@link PeriodicTransactionViewModel} class and supports property change notifications to update the view
 * accordingly.
 *
 * <p>The view model acts as an intermediary between the UI and the business logic, providing getters for
 * various labels used in the periodic transaction view and managing the {@link UserAccountPeriodicTransactionState}.
 * </p>
 *
 * <p>Note that this class constructs the view model with a default view name of "Periodic Transaction"
 * and initializes the transaction state with a new instance of {@link UserAccountPeriodicTransactionState}.</p>
 *
 * @author Jessica Chen
 */
public class UserAccountPeriodicTransactionViewModel extends PeriodicTransactionViewModel<UserAccountPeriodicTransactionState> {

    /**
     * Constructs a {@code UserAccountPeriodicTransactionViewModel} object with the view name set to
     * "Periodic Transaction". Initializes the {@code transactionState} with a new instance of
     * {@link UserAccountPeriodicTransactionState}.
     */
    public UserAccountPeriodicTransactionViewModel() {
        super("Periodic Transaction");
        this.transactionState = new UserAccountPeriodicTransactionState();
    }

    /**
     * Resets the periodic transaction state to default values by creating a new instance of
     * {@link UserAccountPeriodicTransactionState} and setting it as the current state.
     */
    public void resetState() {
        UserAccountPeriodicTransactionState newState = new UserAccountPeriodicTransactionState();
        setState(newState);
    }
}

