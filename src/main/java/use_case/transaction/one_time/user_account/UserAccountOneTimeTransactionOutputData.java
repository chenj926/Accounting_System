package use_case.transaction.one_time.user_account;

import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.one_time.OneTimeTransactionOutputData;

/**
 * The UserAccountOneTimeTransactionOutputData class represents the output data of a one-time transaction operation.
 * It includes details such as the new balance, transaction amount, date, description, category, and status of the use case.
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
public class UserAccountOneTimeTransactionOutputData extends OneTimeTransactionOutputData {

    /**
     * Constructs a UserAccountOneTimeTransactionOutputData object for an outflow transaction with the specified details.
     *
     * @param oneTimeTransaction the outflow transaction entity
     */
    public UserAccountOneTimeTransactionOutputData(OneTimeTransaction oneTimeTransaction) {
        super(oneTimeTransaction);
        // this.useCaseFailed = false;
    }
}