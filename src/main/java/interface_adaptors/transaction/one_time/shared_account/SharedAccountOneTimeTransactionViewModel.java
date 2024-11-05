package interface_adaptors.transaction.one_time.shared_account;

import interface_adaptors.transaction.one_time.OneTimeTransactionViewModel;

/**
 * The {@code SharedOneTimeTransactionViewModel} class extends {@code OneTimeTransactionViewModel}
 * and manages the state and labels for the shared account one-time transaction view.
 * It provides access to various labels used in the shared account one-time transaction view
 * and supports property change notifications to keep the view updated.
 * <p>
 * This view model specifically adds functionality for managing user selections,
 * allowing the specification of which users are responsible for the transaction.
 * </p>
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the user interface reflects the current state of shared account one-time transactions.
 * </p>
 *
 * <p>
 * <b>Author:</b> Xile Chen, Eric Chen
 * </p>
 *
 * @see OneTimeTransactionViewModel
 */
public class SharedAccountOneTimeTransactionViewModel extends
        OneTimeTransactionViewModel<SharedAccountOneTimeTransactionState> { ;
    private final String USER_ID_FIELD_LABEL = "User ID";

    /**
     * Constructs a {@code SharedOneTimeTransactionViewModel} object with the view name set to "Shared Account One Time Transaction".
     * Initializes the state to a new {@code SharedOneTimeTransactionState} instance.
     */
    public SharedAccountOneTimeTransactionViewModel() {
        super("Shared Account One Time Transaction");
        this.state = new SharedAccountOneTimeTransactionState();
    }

    /**
     * Gets the identification label.
     *
     * @return the identification label
     */
    public String getUserIdFieldLabel() {
        return this.USER_ID_FIELD_LABEL;
    }

    /**
     * Gets the label for selecting responsible users.
     *
     * @return the label for selecting responsible users.
     */
    public String getSELECT_USERS_BUTTON_LABEL() {
        return this.USER_ID_FIELD_LABEL;
    }

    /**
     * Resets the shared account one-time transaction state to default values.
     * This method replaces the current state with a new {@code SharedOneTimeTransactionState} instance.
     */
    @Override
    public void resetState() {
        setState(new SharedAccountOneTimeTransactionState());
    }
}

