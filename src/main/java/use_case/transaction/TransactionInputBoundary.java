package use_case.transaction;

/**
 * The TransactionInputBoundary interface provides a generic method for executing transaction operations.
 * Implementations of this interface will handle the process of creating a transaction using the provided input data.
 *
 * @param <I> the type of the transaction input data
 *
 * @author Eric
 * @author Jessica
 */
public interface TransactionInputBoundary<I> {

    /**
     * Executes the transaction operation with the given input data.
     *
     * @param inputData the input data required for the transaction operation
     */
    void execute(I inputData);
}


