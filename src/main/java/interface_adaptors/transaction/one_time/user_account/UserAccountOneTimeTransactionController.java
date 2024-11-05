package interface_adaptors.transaction.one_time.user_account;

import interface_adaptors.transaction.one_time.OneTimeTransactionController;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionInputBoundary;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionInputData;

/**
 * The {@code UserOneTimeTransactionController} class handles user interactions related to
 * one-time transactions for user accounts. It communicates with the use case interactor
 * to execute the one-time transaction process and updates the view model accordingly.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the user interface correctly handles user inputs for one-time transactions and that the
 * transaction process is executed according to the business logic.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Eric Chen
 * </p>
 */
public class UserAccountOneTimeTransactionController extends
        OneTimeTransactionController<
                                UserAccountOneTimeTransactionInputBoundary,
                UserOneTimeTransactionViewModel> {

    /**
     * Constructs a {@code UserOneTimeTransactionController} object with the specified use case interactor
     * and view model.
     *
     * @param userOneTimeTransactionUseCaseInteractor the use case interactor responsible for handling one-time transactions.
     * @param viewModel                               the view model used to update the transaction state.
     */
    public UserAccountOneTimeTransactionController(UserAccountOneTimeTransactionInputBoundary userOneTimeTransactionUseCaseInteractor,
                                                   UserOneTimeTransactionViewModel viewModel) {
        super(userOneTimeTransactionUseCaseInteractor, viewModel);
    }

    /**
     * Executes the one-time transaction process with the given transaction details.
     * This method creates a {@code UserAccountOneTimeTransactionInputData} object containing the
     * transaction details and passes it to the interactor for execution. It then resets the view model state.
     *
     * @param id                   the ID of the transaction.
     * @param amount               the amount of the transaction.
     * @param transactionDate      the date of the transaction.
     * @param transactionDescription the description of the transaction.
     * @param transactionCategory  the category of the transaction.
     */
    public void execute(String id, String amount, String transactionDate, String transactionDescription,
                        String transactionCategory) {
        UserAccountOneTimeTransactionInputData userAccountOneTimeTransactionInputData = new UserAccountOneTimeTransactionInputData(
                id, amount, transactionDate, transactionDescription, transactionCategory
        );
        this.transactionInputBoundary.execute(userAccountOneTimeTransactionInputData);
        this.viewModel.resetState();
    }
}
