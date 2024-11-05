package use_case.transaction;

import entity.transaction.Transaction;

import java.time.LocalDate;

/**
 * The TransactionOutputData class represents the output data of a general transaction operation.
 * It includes details such as the transaction amount, date, description, category, identification, and.
 *
 * @author Jessica
 * @author Eric
 */
public abstract class TransactionOutputData<TransactionType>{
    protected float transactionAmount;
    protected String id;
    protected String transactionDescription;
    protected LocalDate transactionDate;
    protected String transactionCategory;
    protected String[] basicUserInfo;

    public TransactionOutputData(Transaction transaction){
        this.transactionAmount = transaction.getAmount();
        this.transactionDescription = transaction.getDescription();
        this.transactionDate = transaction.getDate();
        this.transactionCategory = transaction.getTransactionCategory();
        this.id = transaction.getIdentification();
        this.basicUserInfo = null;
    }


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

    public String[] getBasicUserInfo() {
        return this.basicUserInfo;
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
