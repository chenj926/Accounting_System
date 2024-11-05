package interface_adaptors.transaction.periodic;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.TransactionPresenter;

/**
 * The {@code PeriodicTransactionPresenter} class extends {@code TransactionPresenter}
 * to handle the presentation logic specific to periodic transactions. It manages the
 * interaction between the periodic transaction view model and the view manager, ensuring
 * that the view is updated correctly based on the transaction state.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the presentation logic for periodic transactions is separate from the business logic.
 * It handles the presentation of error messages and ensures that the view reflects the
 * current transaction state, particularly in the case of a failed transaction attempt.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Eric Chen
 * </p>
 *
 * @param <V> the type of {@code PeriodicTransactionViewModel} managed by this presenter.
 * @param <S> the type of {@code PeriodicTransactionState} associated with the view model.
 */
public abstract class PeriodicTransactionPresenter<
        V extends PeriodicTransactionViewModel<S>,
        S extends PeriodicTransactionState> extends TransactionPresenter<V, S> {

    /**
     * Constructs a {@code PeriodicTransactionPresenter} object with the specified view model
     * and view manager model.
     *
     * @param viewModel   the view model to update the periodic transaction state.
     * @param viewManager the view manager model to manage view transitions.
     */
    public PeriodicTransactionPresenter(V viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the transaction state with the error message and clears any success message
     * to ensure that the view accurately reflects the failure state.
     *
     * @param error the error message to be presented in case of a failed periodic transaction attempt.
     */
    @Override
    public void prepareFailView(String error) {
        S periodicState = this.viewModel.getState();
        periodicState.setErrorMessage(error);
        periodicState.setSuccessMessage(null); // Clear success message on failure
        this.viewModel.setState(periodicState);
        this.viewModel.firePropertyChanged();
    }
}
