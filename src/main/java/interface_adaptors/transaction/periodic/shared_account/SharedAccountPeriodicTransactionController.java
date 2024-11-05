package interface_adaptors.transaction.periodic.shared_account;

import interface_adaptors.transaction.periodic.PeriodicTransactionController;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionInputBoundary;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionInputData;

import java.util.Set;

/**
 * The {@code SharedAccountPeriodicTransactionController} class is responsible for managing user interactions
 * related to shared account periodic transactions. Adhering to Clean Architecture principles, this controller
 * serves as an intermediary between the user interface and the business logic, ensuring that the input data
 * is properly processed and the view model is updated accordingly.
 *
 * <p>This controller communicates with the {@link SharedAccountPeriodicTransactionInputBoundary} to execute
 * the shared account periodic transaction process. It handles the input data, including transaction details
 * and responsible user IDs, and ensures that the view model reflects the current state of the transaction.</p>
 *
 * <p>The {@code SharedAccountPeriodicTransactionController} class is authored by Xile Chen, Jessica Chen,
 * and Eric Chen, with a focus on maintaining a clean, maintainable, and scalable codebase by following Clean
 * Architecture principles.</p>
 *
 * @see SharedAccountPeriodicTransactionInputBoundary
 * @see SharedAccountPeriodicTransactionViewModel
 * @see SharedAccountPeriodicTransactionInputData
 * @see Set
 */
public class SharedAccountPeriodicTransactionController extends PeriodicTransactionController<
        SharedAccountPeriodicTransactionInputBoundary,
        SharedAccountPeriodicTransactionViewModel> {


    /**
     * Constructs a {@code SharedAccountPeriodicTransactionController} object with the specified use case
     * interactor and view model. This constructor ensures that the controller is properly initialized
     * to manage the shared account periodic transaction process.
     *
     * @param sharedAccountPeriodicTransactionInputBoundary the use case interactor for shared account periodic transactions
     * @param viewModel                                     the view model to update the shared account transaction state
     */
    public SharedAccountPeriodicTransactionController( SharedAccountPeriodicTransactionInputBoundary sharedAccountPeriodicTransactionInputBoundary,
            SharedAccountPeriodicTransactionViewModel viewModel) {
        super(sharedAccountPeriodicTransactionInputBoundary, viewModel);
    }

    /**
     * Executes the shared account periodic transaction process with the given transaction details. This method
     * creates an instance of {@link SharedAccountPeriodicTransactionInputData} and passes it to the use case
     * interactor for processing.
     *
     * <p>After executing the transaction, the view model's state is reset to reflect the updated transaction
     * details.</p>
     *
     * @param amount           the amount of the transaction
     * @param startDate        the start date of the transaction
     * @param description      the description of the transaction
     * @param period           the period of the transaction
     * @param endDate          the end date of the transaction
     * @param category         the category of the transaction
     * @param transactionDate  the date of the transaction
     * @param sharedAccountId  the ID of the shared account
     * @param userId           the ID of the responsible user
     */
    public void execute(String amount, String startDate, String description, String period, String endDate,
                        String category, String transactionDate, String sharedAccountId, String userId) {
        // Create input data object for the transaction
        SharedAccountPeriodicTransactionInputData inputData = new SharedAccountPeriodicTransactionInputData(
                amount, startDate, endDate, period, description, category, transactionDate, sharedAccountId, userId
        );

        // Execute the transaction process using the interactor
        this.transactionInputBoundary.execute(inputData);
        this.viewModel.resetState();
    }
}

