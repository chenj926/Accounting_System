package use_case.transaction.one_time.user_account;

import use_case.transaction.one_time.OneTimeTransactionInputData;

/**
 * The UserAccountOneTimeTransactionInputData class represents the input data required for a one-time transaction operation.
 * It includes details such as the transaction amount, date, description, and category.
 *
 * @author Dana
 * @author Eric
 */
public class UserAccountOneTimeTransactionInputData extends OneTimeTransactionInputData {

    /**
     * Constructs a UserAccountOneTimeTransactionInputData object with the specified details.
     *
     * @param transactionAmount      the amount of the transaction
     * @param transactionDate        the date of the transaction
     * @param transactionDescription the description of the transaction
     * @param transactionCategory    the category of the transaction
     */
    public UserAccountOneTimeTransactionInputData(String id, String transactionAmount, String transactionDate,
                                                  String transactionDescription, String transactionCategory) {
        super(id, transactionAmount, transactionDescription, transactionCategory, transactionDate);
    }
}