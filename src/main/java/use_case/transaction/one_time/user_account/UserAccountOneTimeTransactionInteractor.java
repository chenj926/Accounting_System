package use_case.transaction.one_time.user_account;

import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.one_time.OneTimeTransactionInteractor;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

/**
 * The UserAccountOneTimeTransactionInteractor class implements the OneTimeTransactionInputBoundary interface.
 * It handles the process of creating a one-time transaction by validating the input data,
 * interacting with the data access layer, and using the presenter to prepare the output views.
 * <p>
 * This class is responsible for parsing and validating transaction details such as amount and date,
 * and for determining whether the transaction is an inflow or outflow. It then updates the user's
 * account balance accordingly and interacts with the data access object to save the transaction.
 * </p>
 *
 * @author Dana
 * @author Eric
 */
public class UserAccountOneTimeTransactionInteractor extends OneTimeTransactionInteractor<
        UserAccountDataAccessInterface,
        UserAccount,
        UserAccountOneTimeTransactionOutputData,
        UserAccountPeriodicTransactionOutputData,
        UserAccountOneTimeTransactionInputData>
        implements UserAccountOneTimeTransactionInputBoundary {


    /**
     * Constructs a UserAccountOneTimeTransactionInteractor object with the specified data access interface,
     * output boundary, and user account.
     *
     * @param userAccountDataAccessInterface the data access interface for user data
     * @param userAccountOneTimeTransactionOutputBoundary the output boundary for presenting the one-time transaction results
     * @param userAccount the user account associated with the transaction
     */
    public UserAccountOneTimeTransactionInteractor(UserAccountDataAccessInterface userAccountDataAccessInterface,
                                                   UserAccountOneTimeTransactionOutputBoundary userAccountOneTimeTransactionOutputBoundary,
                                                   UserAccount userAccount) {
        super(userAccountDataAccessInterface, userAccountOneTimeTransactionOutputBoundary, userAccount);
    }

    /**
     * Creates an instance of UserAccountOneTimeTransactionOutputData using the specified one-time transaction.
     *
     * @param transaction the one-time transaction used to create the output data
     * @return a UserAccountOneTimeTransactionOutputData object containing the transaction details
     */
    @Override
    protected UserAccountOneTimeTransactionOutputData createOutputData(OneTimeTransaction transaction) {
        return new UserAccountOneTimeTransactionOutputData(transaction);
    }

}