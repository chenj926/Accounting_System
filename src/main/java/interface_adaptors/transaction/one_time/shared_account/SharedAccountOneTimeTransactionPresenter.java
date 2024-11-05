package interface_adaptors.transaction.one_time.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.OneTimeTransactionPresenter;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputBoundary;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;

/**
 * The {@code SharedOneTimeTransactionPresenter} class implements the {@code SharedAccountOneTimeTransactionOutputBoundary}
 * interface and handles the presentation logic for one-time transactions within shared accounts.
 * It updates the view model with the transaction details and manages view transitions based on
 * the outcome of the transaction process.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that the user
 * interface reflects the current state of shared account one-time transactions and that view
 * transitions are handled smoothly based on the transaction results.
 * </p>
 *
 * <p>
 * <b>Author:</b> Eric Chen, Xile Chen, Jessica
 * </p>
 */
public class SharedAccountOneTimeTransactionPresenter extends OneTimeTransactionPresenter<
        SharedAccountOneTimeTransactionViewModel,
        SharedAccountOneTimeTransactionState>
        implements SharedAccountOneTimeTransactionOutputBoundary {

    /**
     * Constructs a {@code SharedOneTimeTransactionPresenter} object with the specified view model
     * and view manager model.
     *
     * @param viewModel   the view model to update the shared account one-time transaction state.
     * @param viewManager the view manager model to manage view transitions.
     */
    public SharedAccountOneTimeTransactionPresenter(SharedAccountOneTimeTransactionViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the success view with the given shared account one-time transaction output data.
     * Updates the transaction state in the view model with the details from the output data
     * and changes the active view to the transaction view.
     *
     * @param data the shared account one-time transaction output data containing transaction details and success status.
     */
    @Override
    public void prepareSuccessView(SharedAccountOneTimeTransactionOutputData data) {
        // Update the current transaction state
        SharedAccountOneTimeTransactionState transactionState = viewModel.getState();

        // Set transaction information
        transactionState.setTransactionDate(data.getTransactionDate().toString());
        transactionState.setTransactionDescription(data.getTransactionDescription());
        transactionState.setTransactionCategory(data.getTransactionCategory());
        transactionState.setId(data.getId());

        this.viewModel.setState(transactionState);
        transactionState.setSuccessMessage("Shared account one-time transaction recorded successfully!");
        viewModel.firePropertyChanged();

        // Change the view to the transaction view
        viewManager.setActiveViewName(viewModel.getViewName());
        viewManager.changeView("Shared Account Homepage Two");
    }
}

