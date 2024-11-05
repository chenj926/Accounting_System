package use_case.transaction.periodic;

import use_case.transaction.TransactionInputData;

/**
 * Abstract base class for periodic transaction input data.
 * It extends TransactionInputData and includes additional fields specific to periodic transactions.
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
public abstract class PeriodicTransactionInputData extends TransactionInputData {
    private String transactionStartDate;
    private String transactionEndDate;
    private String transactionPeriod;
    private String transactionDate;

    /**
     * Constructs a PeriodicTransactionInputData object with the specified details.
     *
     * @param transactionIdentification the unique identification for the transaction
     * @param transactionAmount the amount of the transaction
     * @param transactionStartDate the start date of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionPeriod the period or frequency of the transaction
     * @param transactionEndDate the end date of the transaction
     * @param transactionCategory the category of the transaction
     * @param transactionDate the date of the transaction
     */
    public PeriodicTransactionInputData(String transactionIdentification, String transactionAmount, String transactionStartDate,
                                                   String transactionDescription, String transactionPeriod,
                                                   String transactionEndDate, String transactionCategory, String transactionDate) {
        super(transactionIdentification, transactionAmount, transactionDescription, transactionCategory);
        this.transactionStartDate = transactionStartDate;
        this.transactionEndDate = transactionEndDate;
        this.transactionPeriod = transactionPeriod;
        this.transactionDate = transactionDate;
    }

    /**
     * Gets the start date of the transaction.
     *
     * @return the date of the transaction
     */
    public String getTransactionStartDate() {
        return this.transactionStartDate;
    }

    /**
     * Gets the end date of the transaction.
     *
     * @return the end date of the transaction
     */
    public String getTransactionEndDate() {
        return this.transactionEndDate;
    }

    /**
     * Gets the period(frequency) of the transaction.
     *
     * @return the period of the transaction
     */
    public String getTransactionPeriod() {
        return this.transactionPeriod;
    }

    /**
     * Gets the date of the transaction.
     *
     * @return the date of the transaction
     */
    public String getTransactionDate() {
        return this.transactionDate;
    }
}
