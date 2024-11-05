package use_case.transaction.periodic.shared_account;

import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionInteractor;
//import use_case.transaction.periodic.SharedAccountPeriodicTransactionResponseModel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

/**
 * The {@code SharedAccountPeriodicTransactionInteractor} class manages periodic transactions
 * for shared accounts. It extends {@link PeriodicTransactionInteractor} to handle transaction
 * logic specific to shared accounts and implements the {@link SharedAccountPeriodicTransactionInputBoundary}
 * interface.
 * <p>
 * This class is responsible for validating and processing periodic transactions, including
 * inflows and outflows, for shared accounts. It interacts with data access objects to
 * retrieve and update account information and uses presenters to provide feedback on
 * transaction success or failure.
 * </p>
 *
 * @author Rita
 * @author Xile
 * @author Eric
 * @author Jessica
 */
public class SharedAccountPeriodicTransactionInteractor extends PeriodicTransactionInteractor<
        SharedAccountDataAccessInterface,
        SharedAccount,
        SharedAccountOneTimeTransactionOutputData,
        SharedAccountPeriodicTransactionOutputData,
        SharedAccountPeriodicTransactionInputData>
        implements SharedAccountPeriodicTransactionInputBoundary {

    /**
     * Constructs a SharedAccountPeriodicTransactionInteractor object with the necessary dependencies.
     *
     * @param DAO the data access interface for user account data
     * @param sharedAccount                   the user account associated with the periodic transaction
     * @param presenter  the output boundary for the periodic transaction use case
     */
    public SharedAccountPeriodicTransactionInteractor(SharedAccountDataAccessInterface DAO,
                                                      SharedAccount sharedAccount,
                                                      SharedAccountPeriodicTransactionOutputBoundary presenter) {
        super(DAO, presenter, sharedAccount);
    }

    @Override
    public void execute(SharedAccountPeriodicTransactionInputData periodicTransactionInputData) {
        String userId = periodicTransactionInputData.getId();
        String sharedId = periodicTransactionInputData.getSharedAccountId();
        String stringAmount = periodicTransactionInputData.getTransactionAmount();
        String endDate = periodicTransactionInputData.getTransactionEndDate();
        String description = periodicTransactionInputData.getTransactionDescription();
        String startDate = periodicTransactionInputData.getTransactionStartDate();
        String period = periodicTransactionInputData.getTransactionPeriod();
        String category = periodicTransactionInputData.getTransactionCategory();

        //Set currentDate to today
        LocalDate currentDate = LocalDate.now();

        // Validate input
        if (!checkValid(stringAmount) || !checkValid(startDate) || !checkValid(endDate) ||
                !checkValid(description) || !checkValid(period)) {
            System.out.println("Please do NOT have any empty inputs!");
            return;
        }

        // Parse and validate amount
        float amount = parseAmount(stringAmount);
        if (amount == Float.MIN_VALUE) {
            System.out.println("Incorrect amount! Please ONLY enter number");
            return;
        }

        // Parse and validate dates
        LocalDate localStartDate = parseDate(startDate);
        LocalDate localEndDate = parseDate(endDate);
        if (localStartDate == null || localEndDate == null || localStartDate.isAfter(localEndDate)) {
            System.out.println("Invalid date format or start date after end date! Please enter again!");
            return;
        }

        // Validate and parse the period
        int customPeriod = validateAndParsePeriod(period);
        if (customPeriod == -1) {
            System.out.println("The custom period is not in correct format or is 0, please enter again!");
            return;
        }

        // Determine the ChronoUnit for the period
        ChronoUnit unit = getChronoUnit(period);
        if (!validatePeriod(unit, customPeriod, localStartDate, localEndDate)) {
            System.out.println("Period is longer than the period between start and end date!");
            return;
        }

        boolean isInflow = amount >= 0.0; // Determine if it's an inflow
        processTransactions(isInflow, userId, sharedId, amount, localStartDate, localEndDate, description, period,
                customPeriod, unit, category, currentDate);
    }

    /**
     * Processes the transactions based on whether they are inflow or outflow.
     * <p>
     * This method iterates over the period between the start and end dates,
     * creating and saving inflow or outflow transactions at each step. It updates
     * the shared account's balance and interacts with the data access object to
     * save the transactions.
     * </p>
     *
     * @param isInflow           boolean indicating if the transaction is an inflow
     * @param userId             the user account's id
     * @param shareId            the shared account's id
     * @param amount             the transaction amount
     * @param startDate          the transaction start date
     * @param endDate            the transaction end date
     * @param description        the transaction description
     * @param period             the transaction period
     * @param customPeriod       the custom period in days
     * @param unit               the ChronoUnit of the period
     * @param category           the transaction category
     * @param currentDate the set of user IDs responsible for the transaction
     */
    private void processTransactions(boolean isInflow, String userId, String shareId, float amount, LocalDate startDate,
                                     LocalDate endDate, String description, String period, int customPeriod,
                                     ChronoUnit unit, String category, LocalDate currentDate) {
        // check is the userIds are correct
        if(!checkValidUserId(shareId, userId)) {
            presenter.prepareFailView("This user is not in this Shared Account!");
            return;
        }

        LocalDate date = startDate;
        String userIds = shareId + ";" + userId;
        SharedAccountPeriodicTransactionOutputData finalOutputData = null;

        while (!date.isAfter(currentDate) && !date.isAfter(endDate)) {
            if (isInflow) {
                finalOutputData = processInflowTransaction(userIds, amount, startDate, description, endDate,
                        period, category, date);
            } else {
                finalOutputData = processOutflowTransaction(userIds, amount, startDate, description, endDate,
                        period, category, date);
            }
            // Update current date
            if (unit != ChronoUnit.DAYS) {
                date = date.plus(1, unit);
            } else if (customPeriod == 0) {
                date = date.plus(1, unit);
            } else {
                date = date.plusDays(customPeriod);
            }
        }

        // update the success view only after all transactions are done
        if (finalOutputData != null) {
            this.presenter.prepareSuccessView(finalOutputData);
        }
    }

    /**
     * Checks if the user ID is valid for the shared account.
     * <p>
     * This method verifies if the provided user ID is associated with the specified
     * shared account.
     * </p>
     *
     * @param sharedId the shared account ID
     * @param userId the user ID to be checked
     * @return true if the user ID is valid for the shared account, false otherwise
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
     * Creates output data for a given periodic transaction specific to shared accounts.
     * <p>
     * This method takes a {@link PeriodicTransaction} object and uses it to create an
     * instance of {@link SharedAccountPeriodicTransactionOutputData}. The output data
     * encapsulates the details of the transaction and is used to present the results
     * to the user.
     * </p>
     *
     * @param transaction the {@link PeriodicTransaction} for which to create output data
     * @return an instance of {@link SharedAccountPeriodicTransactionOutputData} containing
     *         the details of the provided transaction
     */
    @Override
    protected SharedAccountPeriodicTransactionOutputData createOutputData(PeriodicTransaction transaction) {
        return new SharedAccountPeriodicTransactionOutputData(transaction);
    }
}






