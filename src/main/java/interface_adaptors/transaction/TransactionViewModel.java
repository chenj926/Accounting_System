package interface_adaptors.transaction;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeSupport;

/**
 * The {@code TransactionViewModel} class extends the {@code ViewModel} class and manages
 * the state and labels for transaction-related views. It provides access to various labels
 * used in the transaction view, such as the amount, description, and button labels, and
 * supports property change notifications to ensure that the view is updated when the state changes.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the user interface reflects the current state of the transaction process and maintains
 * a clear separation between the view and the underlying business logic.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Eric Chen
 * </p>
 */
public class TransactionViewModel extends ViewModel {
    // labels
    protected final String AMOUNT = "Transaction Amount";
    protected final String DESCRIPTION = "Description";
    protected final String CATEGORY_BUTTON = "Transaction Category";
    protected final String SUBMIT_BUTTON = "Submit Transaction";
    protected final String CANCEL_BUTTON = "Cancel";

//    private final TransactionState transactionState = new TransactionState();
//    protected TransactionViewModel currentViewModel;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a {@code TransactionViewModel} object with the specified view name.
     *
     * @param viewName the name of the view associated with this view model.
     */
    public TransactionViewModel(String viewName) {
        super(viewName);
    }

    /**
     * Gets the amount label.
     *
     * @return the amount label
     */
    public String getAmount() {
        return this.AMOUNT;
    }

    /**
     * Gets the description label.
     *
     * @return the description label
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Gets the submit button label.
     *
     * @return the submit button label
     */
    public String getSubmitButton() {
        return this.SUBMIT_BUTTON;
    }

    /**
     * Gets the cancel button label.
     *
     * @return the cancel button label
     */
    public String getCancelButton() {
        return this.CANCEL_BUTTON;
    }
}






