package use_case.transaction.one_time;

import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.TransactionOutputData;

/**
 * The OneTimeTransactionOutputData class represents the output data for one-time transactions.
 * It extends the TransactionOutputData class and provides a way to encapsulate the details
 * of a one-time transaction for presentation.
 *
 * @author Eric
 * @author Dana
 */
public class OneTimeTransactionOutputData extends TransactionOutputData<Transaction> {

    /**
     * Constructs an instance of OneTimeTransactionOutputData with the specified one-time transaction.
     *
     * @param oneTimeTransaction the one-time transaction to be encapsulated in the output data
     */
    public OneTimeTransactionOutputData(OneTimeTransaction oneTimeTransaction) {
        super(oneTimeTransaction);
    }
}
