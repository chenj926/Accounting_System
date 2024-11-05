package interface_adaptors.transaction.one_time;

import interface_adaptors.transaction.TransactionController;
import use_case.transaction.one_time.OneTimeTransactionInputBoundary;

/**
 * The {@code OneTimeTransactionController} class extends {@code TransactionController}
 * to manage the interaction between the one-time transaction use case interactor and
 * the transaction view model. It is responsible for coordinating the flow of data
 * between the user interface and the business logic for one-time transactions.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the presentation logic is cleanly separated from the business logic, and that the
 * view model is correctly updated based on user input and the results of the transaction process.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Eric Chen
 * </p>
 *
 * @param <I> the type of {@code OneTimeTransactionInputBoundary} used by this controller.
 * @param <V> the type of {@code OneTimeTransactionViewModel} managed by this controller.
 */
public abstract class OneTimeTransactionController<
        I extends OneTimeTransactionInputBoundary,
        V extends OneTimeTransactionViewModel> extends TransactionController<I, V> {

    /**
     * Constructs a {@code OneTimeTransactionController} object with the specified use case interactor
     * and view model.
     *
     * @param oneTimeTransactionInteractor the use case interactor responsible for handling one-time transactions.
     * @param viewModel                    the view model used to update the transaction state.
     */
    public OneTimeTransactionController(I oneTimeTransactionInteractor,
                                        V viewModel) {
        super(oneTimeTransactionInteractor, viewModel);
    }

}
