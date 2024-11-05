package use_case.transaction.periodic.user_account;

import use_case.transaction.periodic.PeriodicTransactionInputData;

/**
 * The UserAccountPeriodicTransactionInputData class represents the input data required for a periodic transaction operation.
 * It includes details such as the transaction amount, start date, end date, period, and description.
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
public class UserAccountPeriodicTransactionInputData extends PeriodicTransactionInputData {

    /**
     * Constructs a UserAccountOneTimeTransactionInputData object with the specified details.
     *
     * @param transactionIdentification the amount of the transaction
     * @param transactionAmount      the amount of the transaction
     * @param transactionStartDate        the date of the transaction
     * @param transactionEndDate        the date of the transaction
     * @param transactionPeriod      the period of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     * @param transactionDate        the date of the transaction
     */
    public UserAccountPeriodicTransactionInputData(String transactionIdentification, String transactionAmount, String transactionStartDate,
                                                   String transactionDescription, String transactionPeriod,
                                                   String transactionEndDate, String transactionCategory, String transactionDate) {
        super(transactionIdentification, transactionAmount, transactionStartDate, transactionDescription,
                transactionPeriod, transactionEndDate, transactionCategory, transactionDate);
    }
}