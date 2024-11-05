package use_case.transaction;

/**
 * The TransactionInputData class represents the input data required for a general transaction operation.
 * It includes details such as the transaction amount, description, and category.
 *
 * @author Jessica
 * @author Eric
 */
public abstract class TransactionInputData {
    protected String id;
    protected String transactionAmount;
    protected String transactionDescription;
    protected String transactionCategory;

    /**
     * Constructs a TransactionInputData object with the specified details.
     *
     * @param transactionAmount      the amount of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     */
    public TransactionInputData(String id,
                                String transactionAmount,
                                String transactionDescription,
                                String transactionCategory) {
        this.id = id;
        this.transactionAmount = transactionAmount;
        this.transactionDescription = transactionDescription;
        this.transactionCategory = transactionCategory;

    }

    public String getId() {
        return this.id;
    }

    /**
     * Gets the transaction amount.
     *
     * @return the transaction amount
     */
    public String getTransactionAmount() {
        return this.transactionAmount;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return the description of the transaction
     */
    public String getTransactionDescription() {
        return transactionDescription;
    }

    /**
     * Gets the transaction category.
     *
     * @return the transaction category
     */
    public String getTransactionCategory() {
        return this.transactionCategory;
    }
}
