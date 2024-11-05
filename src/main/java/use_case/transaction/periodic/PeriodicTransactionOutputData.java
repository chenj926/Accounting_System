package use_case.transaction.periodic;

import entity.transaction.Transaction;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.TransactionOutputData;

import java.time.LocalDate;

/**
 * The PeriodicTransactionOutputData class represents the output data for periodic transactions.
 * It includes information about the transaction's start date, end date, and period.
 * This class extends {@link TransactionOutputData} to provide specific details for periodic transactions.
 *
 * @author Jessica
 * @author Eric
 */
public class PeriodicTransactionOutputData extends TransactionOutputData<Transaction> {
    protected LocalDate transactionStartDate;
    protected LocalDate transactionEndDate;
    protected String transactionPeriod;

    /**
     * Constructs a PeriodicTransactionOutputData object with the specified periodic transaction.
     *
     * @param periodicTransaction the periodic transaction to initialize the output data
     */
    public PeriodicTransactionOutputData(PeriodicTransaction periodicTransaction) {
        super(periodicTransaction);
        this.transactionStartDate = periodicTransaction.getStartDate();
        this.transactionEndDate = periodicTransaction.getEndDate();
        this.transactionPeriod = periodicTransaction.getPeriod();
    }

    /**
     * Gets the start date of the transaction.
     *
     * @return the start date of the transaction
     */
    public LocalDate getTransactionStartDate() {
        return this.transactionStartDate;
    }

    /**
     * Gets the end date of the transaction.
     *
     * @return the end date of the transaction
     */
    public LocalDate getTransactionEndDate() {
        return this.transactionEndDate;
    }

    /**
     * Gets the period of the transaction.
     *
     * @return the period of the transaction
     */
    public String getTransactionPeriod() {
        return this.transactionPeriod;
    }

    /**
     * Gets the related account identification of the transaction.
     *
     * @return the related account identification of the transaction
     */
}
