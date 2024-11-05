package interface_adaptors.transaction.periodic.shared_account;

import interface_adaptors.transaction.periodic.PeriodicTransactionViewModel;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionViewModel;

import java.beans.PropertyChangeSupport;

/**
 * The {@code SharedAccountPeriodicTransactionViewModel} class extends the {@link UserAccountPeriodicTransactionViewModel}
 * class and is responsible for managing the state and labels specific to the shared account periodic transaction view.
 * This class adheres to Clean Architecture principles by maintaining a clear separation between the presentation
 * layer and the business logic, ensuring that the UI is updated independently of the underlying processes.
 *
 * <p>This view model provides getters for various labels used in the shared account periodic transaction view,
 * such as responsible user IDs, and supports property change notifications to keep the UI in sync with the
 * transaction state.</p>
 *
 * <p>In addition to handling standard transaction details, this class manages additional shared account
 * information, allowing it to cater specifically to scenarios involving multiple users sharing an account.
 * The view model encapsulates this complexity, providing a clean interface for the UI.</p>
 *
 * <p>The {@code SharedAccountPeriodicTransactionViewModel} class is authored by Xile Chen, with a focus on
 * maintaining a clean, maintainable, and scalable codebase by adhering to the principles of Clean Architecture.</p>
 *
 * @see UserAccountPeriodicTransactionViewModel
 * @see SharedAccountPeriodicTransactionState
 * @see PropertyChangeSupport
 */
public class SharedAccountPeriodicTransactionViewModel extends PeriodicTransactionViewModel<SharedAccountPeriodicTransactionState> {
    private final String USER_ID_FIELD_LABEL = "User ID";
    private final String SELECT_USER = "Users";

    /**
     * Constructs a {@code SharedAccountPeriodicTransactionViewModel} object with the view name set to
     * "Shared Account Periodic Transaction". Initializes the {@code transactionState} with a new instance
     * of {@link SharedAccountPeriodicTransactionState}.
     */
    public SharedAccountPeriodicTransactionViewModel() {
        super("Shared Account Periodic Transaction");
        this.transactionState = new SharedAccountPeriodicTransactionState();
    }

    /**
     * Gets the responsible users label.
     *
     * @return the responsible users label
     */
    public String getUSER_ID_FIELD_LABEL() {
        return this.USER_ID_FIELD_LABEL;
    }
    public String getSELECT_USER() {
        return this.SELECT_USER;
    }


    /**
     * Resets the shared account periodic transaction state to its default values by creating a new instance
     * of {@link SharedAccountPeriodicTransactionState} and setting it as the current state.
     */
    @Override
    public void resetState() {
        SharedAccountPeriodicTransactionState newState = new SharedAccountPeriodicTransactionState();
        setState(newState);
    }

}

