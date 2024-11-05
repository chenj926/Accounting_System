package interface_adaptors.transaction;

import interface_adaptors.ViewManagerModel;

/**
 * The {@code TransactionPresenter} class is an abstract class that handles the presentation logic
 * for transaction-related operations. It manages the interaction between the transaction view model
 * and the view manager, ensuring that the view is updated appropriately based on the transaction state.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, facilitating the separation
 * of concerns by ensuring that the presentation logic is distinct from the underlying business logic.
 * Subclasses are expected to implement specific methods for handling transaction outcomes, such as
 * preparing a view in case of failure.
 * </p>
 *
 * <p>
 * <b>Author:</b> Eric Chen
 * </p>
 *
 * @param <V> the type of {@code TransactionViewModel} managed by this presenter.
 * @param <S> the type of {@code TransactionState} associated with the view model.
 */
public abstract class TransactionPresenter<
        V extends TransactionViewModel,
        S extends TransactionState> {

    protected final V viewModel;
    protected final ViewManagerModel viewManager;

    public TransactionPresenter(V viewModel, ViewManagerModel viewManager) {
        this.viewModel = viewModel;
        this.viewManager = viewManager;
    }

    /**
     * Prepares the fail view with the given error message.
     * Subclasses should implement this method to update the view model with the error message
     * and trigger any necessary view changes when a transaction fails.
     *
     * @param error the error message to be displayed in the fail view.
     */
    public abstract void prepareFailView(String error);
}
