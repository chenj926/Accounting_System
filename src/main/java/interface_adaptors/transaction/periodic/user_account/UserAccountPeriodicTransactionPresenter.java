package interface_adaptors.transaction.periodic.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.PeriodicTransactionPresenter;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputBoundary;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

/**
 * The {@code UserAccountPeriodicTransactionPresenter} class implements the {@link UserAccountPeriodicTransactionOutputBoundary}
 * interface and handles the presentation logic for periodic transactions within the context of a user account.
 * This class adheres to the principles of Clean Architecture by decoupling the presentation layer from the
 * business logic, ensuring a clear separation of concerns.
 *
 * <p>This presenter updates the {@link UserAccountPeriodicTransactionViewModel} with transaction details
 * and manages view transitions through the {@link ViewManagerModel}. It is responsible for preparing the
 * success view and handling the flow of data from the output boundary to the user interface.</p>
 *
 * <p>The {@code UserAccountPeriodicTransactionPresenter} class is authored by Xile Chen, Eric Chen, and Jessica Chen,
 * and follows Clean Architecture principles to maintain a clean, scalable, and maintainable codebase.</p>
 *
 * @see UserAccountPeriodicTransactionOutputBoundary
 * @see PeriodicTransactionPresenter
 * @see UserAccountPeriodicTransactionViewModel
 * @see ViewManagerModel
 */
public class UserAccountPeriodicTransactionPresenter
        extends PeriodicTransactionPresenter<UserAccountPeriodicTransactionViewModel, UserAccountPeriodicTransactionState>
        implements UserAccountPeriodicTransactionOutputBoundary {

    /**
     * Constructs a {@code UserAccountPeriodicTransactionPresenter} object with the specified view model
     * and view manager model. This constructor initializes the presenter, ensuring that the view model
     * and view manager are appropriately linked for handling periodic transaction presentations.
     *
     * @param viewModel  the view model to update the periodic transaction state
     * @param viewManager the view manager model to manage view transitions
     */
    public UserAccountPeriodicTransactionPresenter(UserAccountPeriodicTransactionViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the success view with the given periodic transaction output data. This method updates the
     * transaction state in the view model and triggers the appropriate view transition to display the
     * transaction results to the user.
     *
     * @param data the periodic transaction output data containing transaction details and success status
     */
    @Override
    public void prepareSuccessView(UserAccountPeriodicTransactionOutputData data) {
        // update the current transaction sate
        UserAccountPeriodicTransactionState periodicState = this.viewModel.getState();

        // set info
        periodicState.setTransactionAmount(String.valueOf(data.getTransactionAmount()));
        periodicState.setTransactionStartDate(data.getTransactionStartDate().toString());
        periodicState.setTransactionDescription(data.getTransactionDescription());
        periodicState.setTransactionPeriod(String.valueOf(data.getTransactionPeriod()));
        periodicState.setTransactionEndDate(data.getTransactionEndDate().toString());
        periodicState.setSuccessMessage("Period Transaction Recorded successfully!");
        periodicState.setErrorMessage(null); // Clear err message on failure

        // fire property change
        this.viewModel.setState(periodicState);
        this.viewModel.firePropertyChanged();

        this.viewManager.setActiveViewName(viewModel.getViewName());

        // go back to home page 2
        this.viewManager.changeView("Homepage Two");
    }
}
