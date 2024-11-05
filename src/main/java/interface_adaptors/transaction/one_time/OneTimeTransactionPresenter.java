package interface_adaptors.transaction.one_time;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.TransactionPresenter;

/**
 * The {@code OneTimeTransactionPresenter} class extends {@code TransactionPresenter}
 * to handle the presentation logic specific to one-time transactions. It manages the
 * interaction between the one-time transaction view model and the view manager, ensuring
 * that the view is updated correctly based on the transaction state.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the presentation logic for one-time transactions is separate from the business logic.
 * It handles the presentation of error messages and ensures that the view reflects the
 * current transaction state.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Eric Chen
 * </p>
 *
 * @param <V> the type of {@code OneTimeTransactionViewModel} managed by this presenter.
 * @param <S> the type of {@code OneTimeTransactionState} associated with the view model.
 */
public abstract class OneTimeTransactionPresenter<
        V extends OneTimeTransactionViewModel<S>,
        S extends OneTimeTransactionState> extends TransactionPresenter<V, S> {

    /**
     * Constructs a {@code OneTimeTransactionPresenter} object with the specified view model and view manager model.
     *
     * @param viewModel   the view model to update the one-time transaction state.
     * @param viewManager the view manager model to manage view transitions.
     */
    public OneTimeTransactionPresenter(V viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the fail view with the given error message.
     * Updates the transaction state with the error message and clears any success message
     * to ensure that the view accurately reflects the failure state.
     *
     * @param error the error message to be presented in case of a failed one-time transaction attempt.
     */
    @Override
    public void prepareFailView (String error){
        S oneTimeState = this.viewModel.getState();
        oneTimeState.setErrorMessage(error);
        oneTimeState.setSuccessMessage(null); // Clear success message on failure
        viewModel.firePropertyChanged();
    }
}












