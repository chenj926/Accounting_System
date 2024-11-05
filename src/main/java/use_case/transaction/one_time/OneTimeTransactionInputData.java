package use_case.transaction.one_time;

import use_case.transaction.TransactionInputData;

/**
 * The {@code OneTimeTransactionInputData} class represents the input data required for a one-time transaction operation.
 * <p>
 * It extends {@link TransactionInputData} to include additional details specific to one-time transactions,
 * such as the transaction date.
 * </p>
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
public abstract class OneTimeTransactionInputData extends TransactionInputData {
    private String transactionDate;

    /**
     * Constructs a TransactionInputData object with the specified details.
     *
     * @param id
     * @param transactionAmount      the amount of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     */
    public OneTimeTransactionInputData(String id, String transactionAmount, String transactionDescription,
                                       String transactionCategory, String transactionDate) {
        super(id, transactionAmount, transactionDescription, transactionCategory);
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    public String getTransactionDate() {
        return transactionDate;
    }
}
