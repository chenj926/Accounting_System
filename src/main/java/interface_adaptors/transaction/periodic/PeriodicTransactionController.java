package interface_adaptors.transaction.periodic;

import interface_adaptors.transaction.TransactionController;
import use_case.transaction.periodic.PeriodicTransactionInputBoundary;

/**
 * The {@code PeriodicTransactionController} class extends {@code TransactionController}
 * to manage the interaction between the periodic transaction use case interactor and
 * the transaction view model. It is responsible for coordinating the flow of data
 * between the user interface and the business logic for periodic transactions.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the presentation logic for periodic transactions is cleanly separated from the business logic,
 * and that the view model is correctly updated based on user input and the results of the transaction process.
 * </p>
 *
 * <p>
 * <b>Author:</b> Eric Chen, Jessica Chen
 * </p>
 *
 * @param <I> the type of {@code PeriodicTransactionInputBoundary} used by this controller.
 * @param <V> the type of {@code PeriodicTransactionViewModel} managed by this controller.
 */
public abstract class PeriodicTransactionController<
        I extends PeriodicTransactionInputBoundary,
        V extends PeriodicTransactionViewModel> extends TransactionController<I, V> {

    /**
     * Constructs a {@code PeriodicTransactionController} object with the specified use case interactor
     * and view model.
     *
     * @param periodicTransactionInputBoundary the use case interactor responsible for handling periodic transactions.
     * @param viewModel                        the view model used to update the transaction state.
     */
    public PeriodicTransactionController(I periodicTransactionInputBoundary, V viewModel) {
        super(periodicTransactionInputBoundary, viewModel);
    }

}
