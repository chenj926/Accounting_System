package interface_adaptors.transaction.one_time.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.OneTimeTransactionPresenter;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputBoundary;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;

/**
 * The {@code UserOneTimeTransactionPresenter} class implements the {@code UserAccountOneTimeTransactionOutputBoundary}
 * interface and handles the presentation logic for one-time transactions specific to user accounts.
 * It updates the view model with the transaction details and manages view transitions based on the
 * outcome of the transaction process.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that the user
 * interface reflects the current state of one-time transactions and that view transitions are
 * handled smoothly based on the transaction results.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Xile Chen, Eric Chen
 * </p>
 */
public class UserAccountOneTimeTransactionPresenter extends OneTimeTransactionPresenter<
        UserOneTimeTransactionViewModel,
        UserOneTimeTransactionState>
        implements UserAccountOneTimeTransactionOutputBoundary {

    /**
     * Constructs a {@code UserOneTimeTransactionPresenter} object with the specified view model
     * and view manager model.
     *
     * @param viewModel   the view model to update the one-time transaction state.
     * @param viewManager the view manager model to manage view transitions.
     */
    public UserAccountOneTimeTransactionPresenter(UserOneTimeTransactionViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the success view with the given one-time transaction output data.
     * Updates the transaction state in the view model with the details from the output data
     * and changes the active view to the transaction view.
     *
     * @param data the one-time transaction output data containing transaction details and success status.
     */
    @Override
    public void prepareSuccessView(UserAccountOneTimeTransactionOutputData data) {
        // update the current transaction sate
        UserOneTimeTransactionState oneTimeState = (UserOneTimeTransactionState) viewModel.getState();

        // set info
        oneTimeState.setId(data.getId());
        oneTimeState.setTransactionDate(data.getTransactionDate().toString());
        oneTimeState.setTransactionDescription(data.getTransactionDescription());
        oneTimeState.setTransactionCategory(data.getTransactionCategory());

        this.viewModel.setState(oneTimeState);
        oneTimeState.setSuccessMessage("One time transaction recorded successfully!");
        viewModel.firePropertyChanged();
        viewManager.setActiveViewName(viewModel.getViewName());

        // go back to home page 2
        viewManager.changeView("Homepage Two");
    }

}









