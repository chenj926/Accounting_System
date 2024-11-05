package entity.transaction;

import java.time.LocalDate;

/**
 * The Transaction interface provides a blueprint for a financial transaction entity.
 * It includes methods to get and set transaction details such as date, identification,
 * description, and amount.
 *
 * @author Jessica
 * @author Eric
 */
public abstract class Transaction {
    protected String identification;
    protected float amount;
    protected LocalDate date;
    protected String description;
    protected boolean inflow;
    protected String transactionCategory;

    // getters
    /**
     * Gets the identification of the transaction.
     *
     * @return the identification of the transaction
     */
    public String getIdentification() {
        return this.identification;
    }

    /**
     * Gets the amount of the transaction.
     *
     * @return the amount of the transaction
     */
    public float getAmount() {
        return this.amount;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return the description of the transaction
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the category of the transaction.
     *
     * @return transactionCategory of the transaction
     */
    public String getTransactionCategory() {return this.transactionCategory;}

    /**
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Checks if the transaction is an inflow.
     *
     * @return true if the transaction is an inflow, false otherwise
     */
    public boolean isInflow() {
        return this.inflow;
    }

    // setters
    /**
     * Sets the identification of the transaction.
     *
     * @param identification the new identification of the transaction
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Sets the amount of the transaction and updates the inflow status.
     *
     * @param amount the new amount of the transaction
     */
    public void setAmount(float amount) {
        this.amount = amount;
        this.inflow = (amount>=0);
    }

    /**
     * Sets the description of the transaction.
     *
     * @param description the new description of the transaction
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the category of the transaction.
     *
     * @param transactionCategory of the transaction
     */
    public void setTransactionCategory(String transactionCategory) {this.transactionCategory = transactionCategory;}

    /**
     * Sets the date of the transaction.
     *
     * @param date the new date of the transaction
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
}