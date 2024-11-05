package interface_adaptors.transaction.periodic.user_account;

import interface_adaptors.transaction.periodic.PeriodicTransactionController;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInputData;

/**
 * The {@code UserAccountPeriodicTransactionController} class is responsible for handling user interactions
 * related to periodic transactions within the context of a user account. Adhering to the principles of
 * Clean Architecture, this controller serves as a mediator between the UI and the use case interactor,
 * ensuring that the business logic is decoupled from the presentation layer.
 *
 * <p>This controller interacts with the {@link UserAccountPeriodicTransactionInputBoundary} to execute the
 * periodic transaction process and updates the state of the view through the
 * {@link UserAccountPeriodicTransactionViewModel}.</p>
 *
 * <p>Note that this class is authored by Jessica Chen and adheres to Clean Architecture by ensuring a clear
 * separation of concerns.</p>
 *
 * <p>Jessica Chen's role in the project is to maintain a clean and maintainable codebase while applying
 * best practices in software architecture.</p>
 *
 * <p>The class is also co-authored by Eric Chen, contributing to the implementation of the periodic transaction
 * process.</p>
 *
 * @see PeriodicTransactionController
 * @see UserAccountPeriodicTransactionInputBoundary
 * @see UserAccountPeriodicTransactionViewModel
 */
public class UserAccountPeriodicTransactionController extends PeriodicTransactionController<
        UserAccountPeriodicTransactionInputBoundary,
        UserAccountPeriodicTransactionViewModel> {

    /**
     * Constructs a UserAccountPeriodicTransactionController object with the specified use case interactor and view model.
     *
     * @param userAccountPeriodicTransactionInputBoundary the use case interactor for periodic transactions
     * @param viewModel                        the view model to update the transaction state
     */
    public UserAccountPeriodicTransactionController(UserAccountPeriodicTransactionInputBoundary userAccountPeriodicTransactionInputBoundary,
                                                    UserAccountPeriodicTransactionViewModel viewModel) {
        super(userAccountPeriodicTransactionInputBoundary, viewModel);
    }

    /**
     * Executes the periodic transaction process with the given transaction details.
     *
     * @param id          the identification of the user
     * @param amount      the amount of the transaction
     * @param startDate   the start date of the transaction
     * @param description the description of the transaction
     * @param period      the period of the transaction
     * @param endDate     the end date of the transaction
     */
    public void execute(String id, String amount, String startDate, String description, String period, String endDate, String category, String date) {
        UserAccountPeriodicTransactionInputData userAccountPeriodicTransactionInputData = new UserAccountPeriodicTransactionInputData(id,
                amount, startDate, description, period, endDate, category, date);
        this.transactionInputBoundary.execute(userAccountPeriodicTransactionInputData);
        this.viewModel.resetState();
    }
}

