package interface_adaptors.transaction;

/**
 * The {@code TransactionState} class manages the state information related to a transaction.
 * It includes fields for transaction ID, amount, description, category, success message,
 * and error message. This class provides methods to update and retrieve the transaction state
 * information, allowing for consistent state management throughout the transaction process.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that the
 * state of a transaction is maintained and can be easily accessed and manipulated by other
 * components in the application.
 * </p>
 *
 * <p>
 * <b>Author:</b> Eric Chen
 * </p>
 */
public abstract class TransactionState {
    protected String id;
    protected String transactionAmount;
    protected String transactionDescription;
    protected String transactionCategory;
    protected String successMessage;
    protected String errorMessage;

    /**
     * Constructs a {@code TransactionState} object with default values.
     * Initializes all fields to empty strings or {@code null} as appropriate.
     */
    public TransactionState(){
        this.id = "";
        this.transactionAmount = "0";
        this.transactionDescription = "";
        this.transactionCategory = "";
        this.errorMessage = null;
        this.successMessage = null;
    }

    // getters
    /**
     * Gets the transaction ID.
     *
     * @return the transaction ID.
     */
    public String getId() {
        return this.id;
    }
    /**
     * Gets the transaction amount.
     *
     * @return the transaction amount.
     */
    public String getTransactionAmount() {
        return this.transactionAmount;
    }
    /**
     * Gets the transaction description.
     *
     * @return the transaction description.
     */
    public String getTransactionDescription() {
        return this.transactionDescription;
    }
    /**
     * Gets the transaction category.
     *
     * @return the transaction category.
     */
    public String getTransactionCategory() {
        return this.transactionCategory;
    }
    /**
     * Gets the success message.
     *
     * @return the success message, or {@code null} if none is set.
     */
    public String getSuccessMessage() {
        return this.successMessage;
    }
    /**
     * Gets the error message.
     *
     * @return the error message, or {@code null} if none is set.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    // setters
    /**
     * Sets the transaction ID.
     *
     * @param id the transaction ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Sets the transaction amount.
     *
     * @param transactionAmount the transaction amount.
     */
    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    /**
     * Sets the transaction description.
     *
     * @param transactionDescription the transaction description.
     */
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
    /**
     * Sets the transaction category.
     *
     * @param transactionCategory the transaction category.
     */
    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }
    /**
     * Sets the success message.
     *
     * @param successMessage the success message.
     */
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
    /**
     * Sets the error message.
     *
     * @param errorMessage the error message.
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

