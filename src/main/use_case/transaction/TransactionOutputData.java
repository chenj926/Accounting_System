package use_case.transaction;

import java.time.LocalDate;

public abstract class TransactionOutputData {
    protected float transactionAmount;
    protected String id;
    protected String transactionDescription;
    protected LocalDate transactionDate;
    protected String transactionCategory;

    /**
     * Gets the transaction amount.
     *
     * @return the transaction amount
     */
    public float getTransactionAmount() {
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
     * Gets the identification of the transaction.
     *
     * @return the identification of the transaction
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the category of the transaction.
     *
     * @return the category of the transaction
     */
    public String getTransactionCategory() {
        return this.transactionCategory;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    /**
     * Sets the transaction amount.
     *
     * @param transactionAmount the new transaction amount
     */
    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param transactionDescription the new description of the transaction
     */
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    /**
     * Sets the new category of the transaction.
     *
     * @param transactionCategory the new category of the transaction
     */
    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    /**
     * Sets the date of the transaction.
     *
     * @param transactionDate the new date of the transaction
     */
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
