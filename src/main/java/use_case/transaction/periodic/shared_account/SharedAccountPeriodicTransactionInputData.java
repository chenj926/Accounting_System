package use_case.transaction.periodic.shared_account;

import use_case.transaction.periodic.PeriodicTransactionInputData;

/**
 * The {@code SharedAccountPeriodicTransactionInputData} class represents the input data required for a periodic transaction operation
 * involving a shared account.
 * <p>
 * This class extends {@link PeriodicTransactionInputData} and includes additional details specific to shared accounts,
 * such as the shared account ID and the user ID responsible for the transaction. It encapsulates information about
 * the transaction amount, start date, end date, period, description, category, and the shared account associated with the transaction.
 * </p>
 *
 * @see PeriodicTransactionInputData
 *
 * @author Eric
 * @author Jessica
 */
public class SharedAccountPeriodicTransactionInputData extends PeriodicTransactionInputData {
    private final String sharedAccountId;


    public SharedAccountPeriodicTransactionInputData(
            String transactionAmount,
            String transactionStartDate,
            String transactionEndDate,
            String transactionPeriod,
            String transactionDescription,
            String transactionCategory,
            String transactionDate,
            String sharedAccountId,
            String userId) {
        super(userId, transactionAmount, transactionStartDate, transactionDescription, transactionPeriod, transactionEndDate, transactionCategory, transactionDate);
        this.sharedAccountId = sharedAccountId;
    }

    /**
     * Gets the set of user IDs responsible for the transaction.
     *
     * @return a set of user IDs responsible for the transaction
     */
    public String getSharedAccountId() {
        return this.sharedAccountId;
    }
}
