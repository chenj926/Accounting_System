package interface_adaptors.transaction.periodic.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionPresenter;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputBoundary;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;

/**
 * The {@code SharedAccountPeriodicTransactionPresenter} class implements the {@link SharedAccountPeriodicTransactionOutputBoundary}
 * interface and is responsible for handling the presentation logic associated with shared account periodic transactions.
 * This class adheres to Clean Architecture principles by ensuring that the presentation layer is decoupled from
 * the business logic, allowing for a more maintainable and scalable application structure.
 *
 * <p>This presenter updates the {@link SharedAccountPeriodicTransactionViewModel} with transaction details
 * and manages view transitions through the {@link ViewManagerModel}. It is designed to handle the flow of
 * data from the output boundary to the user interface, preparing the success view and managing any necessary
 * state changes within the view model.</p>
 *
 * <p>The {@code SharedAccountPeriodicTransactionPresenter} class is authored by Xile Chen, Jessica Chen,
 * and Eric Chen, with a focus on following Clean Architecture principles to maintain a clean, maintainable,
 * and scalable codebase.</p>
 *
 * @see SharedAccountPeriodicTransactionOutputBoundary
 * @see PeriodicTransactionPresenter
 * @see SharedAccountPeriodicTransactionViewModel
 * @see ViewManagerModel
 */
public class SharedAccountPeriodicTransactionPresenter
        extends PeriodicTransactionPresenter<SharedAccountPeriodicTransactionViewModel, SharedAccountPeriodicTransactionState>
        implements SharedAccountPeriodicTransactionOutputBoundary {

    /**
     * Constructs a {@code SharedAccountPeriodicTransactionPresenter} object with the specified view model
     * and view manager model. This constructor initializes the presenter, ensuring that the view model
     * and view manager are appropriately linked for handling shared account periodic transaction presentations.
     *
     * @param viewModel   the view model to update the shared account periodic transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public SharedAccountPeriodicTransactionPresenter(SharedAccountPeriodicTransactionViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the success view with the given shared account periodic transaction output data. This method
     * updates the transaction state in the view model and triggers the appropriate view transition to display
     * the transaction results to the user.
     *
     * @param data the shared account periodic transaction output data containing transaction details and success status
     */
    @Override
    public void prepareSuccessView(SharedAccountPeriodicTransactionOutputData data) {
        // Update the current transaction state
        SharedAccountPeriodicTransactionState sharedState = viewModel.getState();

        // Set transaction details
        sharedState.setTransactionAmount(String.valueOf(data.getTransactionAmount()));
        sharedState.setTransactionStartDate(data.getTransactionStartDate().toString());
        sharedState.setTransactionDescription(data.getTransactionDescription());
        sharedState.setTransactionPeriod(String.valueOf(data.getTransactionPeriod()));
        sharedState.setTransactionEndDate(data.getTransactionEndDate().toString());
        sharedState.setTransactionCategory(data.getTransactionCategory());
        sharedState.setId(data.getId());
        sharedState.setSuccessMessage("Shared Account Periodic Transaction Recorded successfully!");
        sharedState.setErrorMessage(null); // Clear error message on success

        // Fire property change
        viewModel.setState(sharedState);
        viewModel.firePropertyChanged();

        // Set the active view and navigate back to the transaction page
        viewManager.setActiveViewName(viewModel.getViewName());
        viewManager.changeView("Shared Account Homepage Two");
    }
}

