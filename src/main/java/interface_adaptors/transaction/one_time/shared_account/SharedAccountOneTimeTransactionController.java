package interface_adaptors.transaction.one_time.shared_account;

import interface_adaptors.transaction.one_time.OneTimeTransactionController;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionInputBoundary;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionInputData;

/**
 * The {@code SharedOneTimeTransactionController} class is responsible for handling user interactions
 * related to one-time transactions for shared accounts. It communicates with the use case interactor
 * to execute the shared account one-time transaction process and updates the view model accordingly.
 * <p>
 * This controller extends functionality to support the selection of responsible users for the transaction,
 * ensuring that the transaction is correctly processed and reflected in the shared account.
 * </p>
 *
 * <p>
 * <b>Author:</b> Xile Chen, Eric Chen, Jessica Chen
 * </p>
 */
public class SharedAccountOneTimeTransactionController extends OneTimeTransactionController<
        SharedAccountOneTimeTransactionInputBoundary,
        SharedAccountOneTimeTransactionViewModel> {

    /**
     * Constructs a {@code SharedOneTimeTransactionController} object with the specified use case interactor
     * and view model.
     *
     * @param sharedAccountTransactionInteractor the use case interactor for shared account one-time transactions.
     * @param viewModel                          the view model used to update the transaction state.
     */
    public SharedAccountOneTimeTransactionController(
            SharedAccountOneTimeTransactionInputBoundary sharedAccountTransactionInteractor,
            SharedAccountOneTimeTransactionViewModel viewModel) {
        super(sharedAccountTransactionInteractor, viewModel);
    }

    /**
     * Executes the shared account one-time transaction process with the given transaction details.
     * This method creates a {@code SharedAccountOneTimeTransactionInputData} object containing the
     * transaction details and passes it to the interactor for execution. It then resets the view model state.
     *
     * @param amount               the amount of the transaction.
     * @param transactionDate      the date of the transaction.
     * @param transactionDescription the description of the transaction.
     * @param transactionCategory  the category of the transaction.
     * @param sharedAccountId      the identifier of the shared account.
     * @param userId               the set of user IDs responsible for the transaction.
     */
    public void execute(
            String amount,
            String transactionDate,
            String transactionDescription,
            String transactionCategory,
            String sharedAccountId, // Make sure sharedAccountId is passed here
            String userId) {

        // Create input data object for the transaction
        SharedAccountOneTimeTransactionInputData transactionInputData = new SharedAccountOneTimeTransactionInputData(
                amount, transactionDate, transactionDescription, transactionCategory, sharedAccountId, userId);

        // Execute the transaction process using the interactor
        this.transactionInputBoundary.execute(transactionInputData);

        // Reset the view model state after processing the transaction
        this.viewModel.resetState();
    }
}


