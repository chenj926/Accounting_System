package interface_adaptors.transaction.one_time;

import interface_adaptors.transaction.TransactionState;

/**
 * The {@code OneTimeTransactionState} class extends {@code TransactionState} to manage
 * the state information specific to one-time transactions. It includes fields for the
 * transaction date, new balance after the transaction, and a flag indicating whether the
 * use case failed.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the state of one-time transactions is maintained and can be easily accessed and modified
 * by other components in the application.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Xile Chen, Eric Chen
 * </p>
 */
public abstract class OneTimeTransactionState extends TransactionState {
    protected String transactionDate;
    protected float newBalance;
    protected boolean useCaseFailed;

    /**
     * Constructs a {@code OneTimeTransactionState} object with default values.
     * Initializes the transaction date to {@code null} and other fields to their default values.
     */
    public OneTimeTransactionState() {
        super();
        this.transactionDate = null;
    }

    /**
     * Gets the transaction date.
     *
     * @return the transaction date.
     */
    public String getTransactionDate() {
        return this.transactionDate;
    }

    /**
     * Gets the new balance after the transaction.
     *
     * @return the new balance after the transaction
     */
    public float getNewBalance() {
        return this.newBalance;
    }

    /**
     * Checks if the use case failed.
     *
     * @return true if the use case failed, false otherwise
     */
    public boolean isUseCaseFailed() {
        return this.useCaseFailed;
    }

    /**
     * Sets the new balance after the transaction.
     *
     * @param newBalance the new balance after the transaction
     */
    public void setNewBalance(float newBalance) {
        this.newBalance = newBalance;
    }

    /**
     * Sets the use case failure status.
     *
     * @param useCaseFailed the new use case failure status
     */
    public void setUseCaseFailed(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Sets the transaction date.
     *
     * @param transactionDate the new transaction date
     */
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
