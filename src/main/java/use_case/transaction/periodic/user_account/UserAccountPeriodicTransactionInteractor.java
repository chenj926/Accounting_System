package use_case.transaction.periodic.user_account;

import data_access.account.user_account.UserAccountDataAccessInterface;

import entity.account.user_account.UserAccount;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionInteractor;

/**
 * The UserAccountPeriodicTransactionInteractor class implements the UserAccountPeriodicTransactionInputBoundary interface.
 * It handles the process of creating a periodic transaction by validating the input data,
 * interacting with the data access layer, and using the presenter to prepare the output views.
 *
 * @author Eric
 * @author Dana
 */
public class UserAccountPeriodicTransactionInteractor extends PeriodicTransactionInteractor<
        UserAccountDataAccessInterface,
        UserAccount,
        UserAccountOneTimeTransactionOutputData,
        UserAccountPeriodicTransactionOutputData,
        UserAccountPeriodicTransactionInputData> implements UserAccountPeriodicTransactionInputBoundary {

    /**
     * Constructs a UserAccountPeriodicTransactionInteractor object with the specified data access interface,
     * output boundary, and user account.
     *
     * @param userAccountDataAccessInterface the data access interface for user data
     * @param userAccountPeriodicTransactionOutputBoundary the output boundary for presenting the periodic transaction results
     * @param userAccount the user account associated with the transaction
     */
    public UserAccountPeriodicTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                                    UserAccountPeriodicTransactionOutputBoundary userAccountPeriodicTransactionOutputBoundary,
                                                    UserAccount userAccount) {
        super(userAccountDataAccessInterface, userAccountPeriodicTransactionOutputBoundary, userAccount);
    }


    /**
     * Creates output data for a given periodic transaction.
     * <p>
     * This method takes a {@link PeriodicTransaction} object and uses it to create an
     * instance of {@link UserAccountPeriodicTransactionOutputData}. The output data
     * encapsulates the details of the transaction and is used to present the results
     * to the user.
     * </p>
     *
     * @param transaction the {@link PeriodicTransaction} for which to create output data
     * @return an instance of {@link UserAccountPeriodicTransactionOutputData} containing
     *         the details of the provided transaction
     * @author [Your Name]
     */
    @Override
    protected UserAccountPeriodicTransactionOutputData createOutputData(PeriodicTransaction transaction) {
        return new UserAccountPeriodicTransactionOutputData(transaction);
    }

}