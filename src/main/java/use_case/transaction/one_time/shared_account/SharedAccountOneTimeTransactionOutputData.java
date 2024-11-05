package use_case.transaction.one_time.shared_account;

import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.one_time.OneTimeTransactionOutputData;

/**
 * The SharedAccountOneTimeTransactionOutputData class represents the output data of a one-time transaction operation
 * that involves a shared account.
 * It includes details such as the new balance of the shared account and the set of users responsible for the transaction.
 *
 * @author Rita
 * @author Xile
 * @author Eric
 */
public class SharedAccountOneTimeTransactionOutputData extends OneTimeTransactionOutputData {

    /**
     * Constructs a SharedAccountOneTimeTransactionOutputData object with the specified details.
     *
     * @param oneTimeTransaction      the one-time transaction entity
     */
    public SharedAccountOneTimeTransactionOutputData(OneTimeTransaction oneTimeTransaction) {
        super(oneTimeTransaction);
    }
}

