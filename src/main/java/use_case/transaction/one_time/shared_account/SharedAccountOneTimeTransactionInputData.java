package use_case.transaction.one_time.shared_account;

import use_case.transaction.one_time.OneTimeTransactionInputData;

/**
 * The SharedAccountOneTimeTransactionInputData class represents the input data required for a one-time transaction operation
 * that involves a shared account.
 * It includes details such as the transaction amount, date, description, category, the shared account identifier,
 * and the set of users responsible for the transaction.
 *
 * @author Rita
 * @author Xile
 * @author Eric
 */
public class SharedAccountOneTimeTransactionInputData extends OneTimeTransactionInputData {
    private final String sharedAccountId;

    /**
     * Constructs a SharedAccountOneTimeTransactionInputData object with the specified details.
     *
     * @param transactionAmount      the amount of the transaction
     * @param transactionDate        the date of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     * @param sharedAccountId        the identifier of the shared account
     * @param userId    a set of user IDs responsible for the transaction
     */
    public SharedAccountOneTimeTransactionInputData(
            String transactionAmount,
            String transactionDate,
            String transactionDescription,
            String transactionCategory,
            String sharedAccountId,
            String userId) {
        super(userId, transactionAmount, transactionDescription, transactionCategory, transactionDate);
        this.sharedAccountId = sharedAccountId;
    }

    /**
     * Gets the identifier of the shared account.
     *
     * @return the identifier of the shared account
     */
    public String getSharedAccountId() {
        return sharedAccountId;
    }
}

