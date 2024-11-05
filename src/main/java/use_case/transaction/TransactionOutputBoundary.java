package use_case.transaction;

/**
 * The TransactionOutputBoundary interface provides methods for preparing the view based on the outcome of a transaction operation.
 * Implementations of this interface will handle the presentation logic for successful and failed transaction attempts.
 *
 * @author Jessica
 */
public interface TransactionOutputBoundary<TransactionOutputData>{
    /**
     * Prepares the success view with the given transaction output data.
     *
     * @param transactions the transaction output data containing transaction information and new balance
     */
    void prepareSuccessView(TransactionOutputData transactions);

    /**
     * Prepares the fail view with the given error message.
     *
     * @param error the error message to be presented in case of a failed transaction attempt
     */
    void prepareFailView(String error);
}