package use_case.update_periodic_at_login.shared_account;

import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;
import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInteractor;

import java.time.LocalDate;
import java.util.Map;

/**
 * The SharedAccountUpdatePeriodicAtLoginInteractor class handles the update of
 * periodic transactions for shared accounts
 * when a user logs in. It extends
 * {@link AccountUpdatePeriodicAtLoginInteractor} and implements the
 * {@link SharedAccountUpdatePeriodicAtLoginInputBoundary} interface.
 * <p>
 * This class processes periodic transactions based on the user’s login date,
 * updating the shared account’s total income,
 * total outflow, and balance. It interacts with the data access interface for
 * shared accounts to read and save transactions
 * and update account details.
 * </p>
 *
 * @author Jessica
 */
public class SharedAccountUpdatePeriodicAtLoginInteractor extends
        AccountUpdatePeriodicAtLoginInteractor<SharedAccountDataAccessInterface, SharedAccount, SharedAccountPeriodicTransactionOutputData>
        implements SharedAccountUpdatePeriodicAtLoginInputBoundary {

    /**
     * Constructs an SharedAccountUpdatePeriodicAtLoginInteractor with the given
     * SharedAccountDataAccessInterface.
     *
     * @param sharedDataAccessObject the data access object for user accounts
     */
    public SharedAccountUpdatePeriodicAtLoginInteractor(SharedAccountDataAccessInterface sharedDataAccessObject) {
        super(sharedDataAccessObject);
    }

    /**
     * Executes the update of periodic transactions for the given user based on the
     * provided input data.
     *
     * @param sharedAccountUpdatePeriodicAtLoginInputData the input data containing
     *                                                    user identification and
     *                                                    current date
     */
    @Override
    public void execute(SharedAccountUpdatePeriodicAtLoginInputData sharedAccountUpdatePeriodicAtLoginInputData) {
        // Get the information from the input data
        String sharedId = sharedAccountUpdatePeriodicAtLoginInputData.getIdentification();
        LocalDate currentDate = sharedAccountUpdatePeriodicAtLoginInputData.getCurrentDate();

        // get the user account, transactions
        SharedAccount sharedAccount = dataAccessObject.getById(sharedId);
        LocalDate lastLoginDate = sharedAccount.getLastLoginDate();

        // Get the latest transactions for each unique set of properties
        Map<String, PeriodicTransaction> latestTransactionsMap = getLatestTransactionsMap(sharedId, lastLoginDate);

        // Process each latest transaction from the last recorded date up to the current
        // date
        for (PeriodicTransaction periodicTransaction : latestTransactionsMap.values()) {
            System.out.println("process transaction");
            processTransaction(sharedAccount, periodicTransaction, currentDate);
        }

        sharedAccount.setLastLoginDate(currentDate);
        dataAccessObject.update(sharedAccount);

    }

    /**
     * Processes an inflow transaction by creating a new PeriodicInflow object,
     * updating the user's total income
     * and balance, and saving the transaction through the data access object.
     *
     * @param sharedAccount          the user account
     * @param periodicTransaction    the periodic transaction
     * @param sharedDataAccessObject the data access object for user accounts
     * @param date                   the date of the transaction
     */
    protected void processInflow(SharedAccount sharedAccount, PeriodicTransaction periodicTransaction,
            SharedAccountDataAccessInterface sharedDataAccessObject, LocalDate date) {

        // Create new periodic inflow
        PeriodicInflow periodicInflow = new PeriodicInflow(
                periodicTransaction.getIdentification(),
                periodicTransaction.getAmount(),
                periodicTransaction.getStartDate(),
                periodicTransaction.getDescription(),
                periodicTransaction.getEndDate(),
                periodicTransaction.getPeriod(),
                periodicTransaction.getTransactionCategory());

        // update date
        periodicInflow.setDate(date);

        // Create a new PeriodicInflow object
        float totalIncome = sharedAccount.getTotalIncome() + periodicTransaction.getAmount();
        sharedAccount.setTotalIncome(totalIncome);

        // Update the user's total income and balance
        float totalBalance = sharedAccount.getTotalBalance() + periodicTransaction.getAmount();
        sharedAccount.setTotalBalance(totalBalance);

        // Update through the DAO
        SharedAccountPeriodicTransactionOutputData outputData = new SharedAccountPeriodicTransactionOutputData(
                periodicInflow);
        sharedDataAccessObject.saveTransaction(null, outputData, true);
        sharedDataAccessObject.update(sharedAccount);
    }

    /**
     * Processes an outflow transaction by creating a new PeriodicOutflow object,
     * updating the user's total outflow
     * and balance, and saving the transaction through the data access object.
     *
     * @param sharedAccount          the user account
     * @param periodicTransaction    the periodic transaction
     * @param sharedDataAccessObject the data access object for user accounts
     * @param date                   the date of the transaction
     */
    protected void processOutflow(SharedAccount sharedAccount, PeriodicTransaction periodicTransaction,
            SharedAccountDataAccessInterface sharedDataAccessObject, LocalDate date) {
        // Create new periodic outflow
        PeriodicOutflow periodicOutflow = new PeriodicOutflow(
                periodicTransaction.getIdentification(),
                periodicTransaction.getAmount(),
                periodicTransaction.getStartDate(),
                periodicTransaction.getDescription(),
                periodicTransaction.getEndDate(),
                periodicTransaction.getPeriod(),
                periodicTransaction.getTransactionCategory());

        // update date
        periodicOutflow.setDate(date);

        // Create a new PeriodicInflow object
        float totalOutflow = sharedAccount.getTotalOutflow() + periodicTransaction.getAmount();
        sharedAccount.setTotalOutflow(totalOutflow);

        // Update the user's total income and balance
        float totalBalance = sharedAccount.getTotalBalance() + periodicTransaction.getAmount();
        sharedAccount.setTotalBalance(totalBalance);

        // Update through the DAO
        SharedAccountPeriodicTransactionOutputData outputData = new SharedAccountPeriodicTransactionOutputData(
                periodicOutflow);
        sharedDataAccessObject.saveTransaction(null, outputData, true);
        sharedDataAccessObject.update(sharedAccount);
    }
}
