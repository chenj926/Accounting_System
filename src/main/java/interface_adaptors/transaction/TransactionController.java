package interface_adaptors.transaction;

import use_case.transaction.TransactionInputBoundary;

/**
 * The {@code TransactionController} class is an abstract class that handles user input
 * for transaction-related operations. It coordinates the interaction between the transaction
 * input boundary (use case interactor) and the transaction view model, ensuring that user
 * actions are correctly processed and that the view is updated accordingly.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, providing a clear
 * separation between user interface logic and business logic. Subclasses should implement
 * specific methods to handle different types of transaction interactions.
 * </p>
 *
 * <p>
 * <b>Author:</b> Eric Chen
 * </p>
 *
 * @param <I> the type of {@code TransactionInputBoundary} used by this controller.
 * @param <V> the type of {@code TransactionViewModel} managed by this controller.
 */
public abstract class TransactionController<
        I extends TransactionInputBoundary,
        V extends TransactionViewModel> {
    protected final I transactionInputBoundary;
    protected final V viewModel;

    /**
     * Constructs a {@code TransactionController} with the specified transaction input boundary
     * and transaction view model.
     *
     * @param transactionInputBoundary the use case interactor responsible for processing transaction inputs.
     * @param viewModel                the transaction view model used to update the view.
     */
    public TransactionController(I transactionInputBoundary, V viewModel) {
        this.transactionInputBoundary = transactionInputBoundary;
        this.viewModel = viewModel;
    }

}
