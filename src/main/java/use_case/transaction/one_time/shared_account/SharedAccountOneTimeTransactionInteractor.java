package use_case.transaction.one_time.shared_account;

import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;
import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.one_time.OneTimeTransactionInteractor;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;

import java.time.LocalDate;
import java.util.Set;

/**
 * The SharedAccountOneTimeTransactionInteractor class implements the TransactionInputBoundary
 * interface for handling one-time transactions for a shared account.
 *
 * @author Rita
 * @author Xile
 * @author Eric
 */
public class SharedAccountOneTimeTransactionInteractor extends OneTimeTransactionInteractor<
        SharedAccountDataAccessInterface,
        SharedAccount,
        SharedAccountOneTimeTransactionOutputData,
        SharedAccountPeriodicTransactionOutputData,
        SharedAccountOneTimeTransactionInputData>
        implements SharedAccountOneTimeTransactionInputBoundary { // Use the generic interface

    private final SharedAccountDataAccessInterface sharedAccountDataAccessInterface;
    private final SharedAccount sharedAccount;
    private final SharedAccountOneTimeTransactionOutputBoundary presenter;

    /**
     * Constructs a SharedAccountOneTimeTransactionInteractor object with the specified data access interface,
     * output boundary, and shared account.
     *
     * @param sharedAccountDataAccessInterface the data access interface for shared account data
     * @param presenter                        the output boundary for presenting the one-time transaction results
     * @param sharedAccount                    the shared account associated with the transaction
     */
    public SharedAccountOneTimeTransactionInteractor(SharedAccountDataAccessInterface sharedAccountDataAccessInterface,
                                                     SharedAccountOneTimeTransactionOutputBoundary presenter,
                                                     SharedAccount sharedAccount) {
        super(sharedAccountDataAccessInterface, presenter, sharedAccount);
        this.sharedAccountDataAccessInterface = sharedAccountDataAccessInterface;
        this.presenter = presenter;
        this.sharedAccount = sharedAccount;
    }

    /**
     * Executes the shared account one-time transaction process with the given input data.
     * This method validates the input data, processes the transaction, and updates the shared account.
     *
     * @param inputData the input data required for the shared account transaction process
     */
    @Override
    public void execute(SharedAccountOneTimeTransactionInputData inputData) {
        String sharedId = inputData.getSharedAccountId();
        String userId = inputData.getId();
        String stringAmount = inputData.getTransactionAmount();
        String date = inputData.getTransactionDate();
        String description = inputData.getTransactionDescription();
        String category = inputData.getTransactionCategory();

        // Validate input fields
        if (!checkValid(stringAmount) || !checkValid(date) || !checkValid(description) || !checkValid(category)) {
            presenter.prepareFailView("Please do NOT have any empty inputs!");
            return;
        }

        // Parse and validate the amount using the inherited method
        float amount = parseAmount(stringAmount);
        if (amount == Float.MIN_VALUE) {
            presenter.prepareFailView("Incorrect amount! Please ONLY enter numbers.");
            return;
        }

        // Parse and validate the date using the inherited method
        LocalDate localDate = parseDate(date);
        if (localDate == null) {
            presenter.prepareFailView("Invalid date format! Please enter again.");
            return;
        }

        // Determine transaction type
        boolean isInflow = amount >= 0.0;

        // Process the transaction
        if (isInflow) {
            processInflow(sharedId, userId, amount, localDate, description, category);
        } else {
            processOutflow(sharedId, userId, amount, localDate, description, category);
        }
    }

    /**
     * Processes an inflow transaction for the shared account.
     *
     * @param amount               the transaction amount
     * @param date                 the transaction date
     * @param description          the transaction description
     * @param category             the transaction category
     */
    private void processInflow(String sharedId, String userId, float amount, LocalDate date, String description,
                               String category) {
        // Update shared account income and balance
        float totalIncome = sharedAccount.getTotalIncome() + amount;
        this.sharedAccount.setTotalIncome(totalIncome);
        float totalBalance = sharedAccount.getTotalBalance() + amount;
        this.sharedAccount.setTotalBalance(totalBalance);

        if (!checkValidUserId(sharedId, userId)) {
            presenter.prepareFailView("This user is not in this Shared Account!");
            return;
        }

        // package shareids+userids
        String ids = sharedId + ";" + userId;

        // Create inflow transaction
        OneTimeInflow oneTimeInflow = new OneTimeInflow(ids, amount, date, description, category);

        // Prepare output data
        SharedAccountOneTimeTransactionOutputData outputData = new SharedAccountOneTimeTransactionOutputData(oneTimeInflow);

        // Save this transaction
        sharedAccountDataAccessInterface.saveTransaction(outputData, null, false);
        sharedAccountDataAccessInterface.update(sharedAccount);
        presenter.prepareSuccessView(outputData);
    }

    /**
     * Processes an outflow transaction for the shared account.
     *
     * @param amount               the transaction amount
     * @param date                 the transaction date
     * @param description          the transaction description
     * @param category             the transaction category
     */
    private void processOutflow(String sharedId, String userId, float amount, LocalDate date, String description,
                                String category) {

        if (!checkValidUserId(sharedId, userId)) {
            presenter.prepareFailView("This user is not in this Shared Account!");
            return;
        }
        // Update shared account outflow and balance
        String ids = sharedId + ";" + userId;
        float totalOutflow = sharedAccount.getTotalOutflow() + amount;
        sharedAccount.setTotalOutflow(totalOutflow);
        float totalBalance = sharedAccount.getTotalBalance() - amount;
        sharedAccount.setTotalBalance(totalBalance);

        // Create outflow transaction
        OneTimeOutflow oneTimeOutflow = new OneTimeOutflow(ids, amount, date, description, category);

        // Prepare output data
        SharedAccountOneTimeTransactionOutputData outputData = new SharedAccountOneTimeTransactionOutputData(oneTimeOutflow);

        // Save this transaction
        sharedAccountDataAccessInterface.saveTransaction(outputData, null, false);
        sharedAccountDataAccessInterface.update(sharedAccount);
        presenter.prepareSuccessView(outputData);
    }

    /**
     * Checks if the given user ID is valid within the specified shared account.
     *
     * @param sharedId the ID of the shared account
     * @param userId   the ID of the user to check
     * @return true if the user ID is valid within the shared account, false otherwise
     */
    private boolean checkValidUserId(String sharedId, String userId) {
        SharedAccount account = this.userDataAccessObject.getById(sharedId);
        Set<String> userIds = account.getSharedUserIdentifications();
        for (String id : userIds) {
            if (id.equals(userId)){
                return true;
            }
        }
        return false;
    }

    /**
     * Creates an instance of SharedAccountOneTimeTransactionOutputData using the specified one-time transaction.
     *
     * @param transaction the one-time transaction used to create the output data
     * @return a SharedAccountOneTimeTransactionOutputData object containing the transaction details
     */
    @Override
    protected SharedAccountOneTimeTransactionOutputData createOutputData(OneTimeTransaction transaction) {
        return new SharedAccountOneTimeTransactionOutputData(transaction);
    }


}








