package use_case.update_periodic_at_login.user_account;

import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;
import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInteractor;

import java.time.LocalDate;
import java.util.Map;

/**
 * The UserAccountUpdatePeriodicAtLoginInteractor class is responsible for
 * updating periodic transactions
 * when a user logs in. It processes both inflow and outflow transactions based
 * on their periodicity
 * and ensures that the user's account balances are updated accordingly.
 *
 * @author Jessica
 */
public class UserAccountUpdatePeriodicAtLoginInteractor extends
        AccountUpdatePeriodicAtLoginInteractor<UserAccountDataAccessInterface, UserAccount, UserAccountPeriodicTransactionOutputData>
        implements UserAccountUpdatePeriodicAtLoginInputBoundary {

    /**
     * Constructs an UserAccountUpdatePeriodicAtLoginInteractor with the given
     * UserAccountDataAccessInterface.
     *
     * @param userDataAccessObject the data access object for user accounts
     */
    public UserAccountUpdatePeriodicAtLoginInteractor(UserAccountDataAccessInterface userDataAccessObject) {
        super(userDataAccessObject);
    }

    /**
     * Executes the update of periodic transactions for the given user based on the
     * provided input data.
     *
     * @param userAccountUpdatePeriodicAtLoginInputData the input data containing
     *                                                  user identification and
     *                                                  current date
     */
    @Override
    public void execute(UserAccountUpdatePeriodicAtLoginInputData userAccountUpdatePeriodicAtLoginInputData) {
        // Get the information from the input data
        String userId = userAccountUpdatePeriodicAtLoginInputData.getIdentification();
        LocalDate currentDate = userAccountUpdatePeriodicAtLoginInputData.getCurrentDate();

        // get the user account, transactions
        UserAccount userAccount = dataAccessObject.getById(userId);
        LocalDate lastLoginDate = userAccount.getLastLoginDate();

        // Get the latest transactions for each unique set of properties
        Map<String, PeriodicTransaction> latestTransactionsMap = getLatestTransactionsMap(userId, lastLoginDate);

        // Process each latest transaction from the last recorded date up to the current
        // date
        for (PeriodicTransaction periodicTransaction : latestTransactionsMap.values()) {
            System.out.println("process transaction");
            processTransaction(userAccount, periodicTransaction, currentDate);
        }

        userAccount.setLastLoginDate(currentDate);
        dataAccessObject.update(userAccount);

    }

    /**
     * Processes an inflow transaction by creating a new PeriodicInflow object,
     * updating the user's total income
     * and balance, and saving the transaction through the data access object.
     *
     * @param userAccount          the user account
     * @param periodicTransaction  the periodic transaction
     * @param userDataAccessObject the data access object for user accounts
     * @param date                 the date of the transaction
     */
    protected void processInflow(UserAccount userAccount, PeriodicTransaction periodicTransaction,
            UserAccountDataAccessInterface userDataAccessObject, LocalDate date) {

        // Create new periodic inflow
        PeriodicInflow periodicInflow = new PeriodicInflow(
                userAccount.getIdentification(),
                periodicTransaction.getAmount(),
                periodicTransaction.getStartDate(),
                periodicTransaction.getDescription(),
                periodicTransaction.getEndDate(),
                periodicTransaction.getPeriod(),
                periodicTransaction.getTransactionCategory());

        // update date
        periodicInflow.setDate(date);

        // Create a new PeriodicInflow object
        float totalIncome = userAccount.getTotalIncome() + periodicTransaction.getAmount();
        userAccount.setTotalIncome(totalIncome);

        // Update the user's total income and balance
        float totalBalance = userAccount.getTotalBalance() + periodicTransaction.getAmount();
        userAccount.setTotalBalance(totalBalance);

        // Update through the DAO
        UserAccountPeriodicTransactionOutputData outputData = new UserAccountPeriodicTransactionOutputData(
                periodicInflow);
        userDataAccessObject.saveTransaction(null, outputData, true);
        userDataAccessObject.update(userAccount);
    }

    /**
     * Processes an outflow transaction by creating a new PeriodicOutflow object,
     * updating the user's total outflow
     * and balance, and saving the transaction through the data access object.
     *
     * @param userAccount          the user account
     * @param periodicTransaction  the periodic transaction
     * @param userDataAccessObject the data access object for user accounts
     * @param date                 the date of the transaction
     */
    protected void processOutflow(UserAccount userAccount, PeriodicTransaction periodicTransaction,
            UserAccountDataAccessInterface userDataAccessObject, LocalDate date) {

        // Create new periodic outflow
        PeriodicOutflow periodicOutflow = new PeriodicOutflow(
                userAccount.getIdentification(),
                periodicTransaction.getAmount(),
                periodicTransaction.getStartDate(),
                periodicTransaction.getDescription(),
                periodicTransaction.getEndDate(),
                periodicTransaction.getPeriod(),
                periodicTransaction.getTransactionCategory());

        // update date
        periodicOutflow.setDate(date);

        // Create a new PeriodicInflow object
        float totalOutflow = userAccount.getTotalOutflow() + periodicTransaction.getAmount();
        userAccount.setTotalOutflow(totalOutflow);

        // Update the user's total income and balance
        float totalBalance = userAccount.getTotalBalance() + periodicTransaction.getAmount();
        userAccount.setTotalBalance(totalBalance);

        // Update through the DAO
        UserAccountPeriodicTransactionOutputData outputData = new UserAccountPeriodicTransactionOutputData(
                periodicOutflow);
        userDataAccessObject.saveTransaction(null, outputData, true);
        userDataAccessObject.update(userAccount);
    }

}