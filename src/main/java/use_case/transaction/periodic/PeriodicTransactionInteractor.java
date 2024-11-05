package use_case.transaction.periodic;

import data_access.account.AccountDataAccessInterface;
import entity.account.Account;
import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.TransactionInteractor;
import use_case.transaction.one_time.OneTimeTransactionOutputData;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Abstract base class for handling periodic transactions.
 * <p>
 * This class provides the implementation for executing periodic transactions, including validation,
 * processing inflows and outflows, and interacting with the data access object.
 * </p>
 *
 * @param <DAO> the type of data access interface
 * @param <A> the type of account
 * @param <O> the type of one-time transaction output data
 * @param <P> the type of periodic transaction output data
 * @param <I> the type of periodic transaction input data
 *
 * @author Eric
 * @author Jessica
 */
public abstract class PeriodicTransactionInteractor <
        DAO extends AccountDataAccessInterface<A, O, P>,
        A extends Account,
        O extends OneTimeTransactionOutputData,
        P extends PeriodicTransactionOutputData,
        I extends PeriodicTransactionInputData> extends TransactionInteractor<DAO, A, O, P> implements PeriodicTransactionInputBoundary<I> {

    protected final PeriodicTransactionOutputBoundary<P> presenter;

    /**
     * Constructs a PeriodicTransactionInteractor object with the specified data access interface,
     * presenter, and account.
     *
     * @param dataAccessInterface the data access interface for user data
     * @param presenter the output boundary for periodic transactions
     * @param account the user account associated with the transactions
     */
    protected PeriodicTransactionInteractor(DAO dataAccessInterface,
                                            PeriodicTransactionOutputBoundary<P> presenter,
                                            A account) {
        super(dataAccessInterface, account);
        this.presenter = presenter;
    }

    /**
     * Executes the periodic transaction use case.
     * <p>
     * This method validates the input data, including amount, start date, end date, description,
     * period, and category. It processes either inflow or outflow transactions based on the amount
     * and updates the user's account balance accordingly. It handles different periods, including custom
     * periods in days, and ensures that transactions are created within the specified date range.
     * </p>
     *
     * @param userAccountPeriodicTransactionInputData the input data for the periodic transaction
     */
    @Override
    public void execute(I userAccountPeriodicTransactionInputData) {
        String identification = userAccountPeriodicTransactionInputData.getId();
        String stringAmount = userAccountPeriodicTransactionInputData.getTransactionAmount();
        String endDate = userAccountPeriodicTransactionInputData.getTransactionEndDate();
        String description = userAccountPeriodicTransactionInputData.getTransactionDescription();
        String startDate = userAccountPeriodicTransactionInputData.getTransactionStartDate();
        String period = userAccountPeriodicTransactionInputData.getTransactionPeriod();
        String category = userAccountPeriodicTransactionInputData.getTransactionCategory();

        //Set currentDate to today
        LocalDate currentDate = LocalDate.now();

        // if user entered empty input in one or more of the input fields
        if(!checkValid(stringAmount) || !checkValid(startDate) || !checkValid(endDate) ||
                !checkValid(description) || !checkValid(period)) {
            presenter.prepareFailView("Please do NOT have any empty inputs!");
            return;
        }

        // Parse and validate the amount
        float amount = parseAmount(stringAmount);
        // we set float.MIN VAL to be the false output of the helper
        if (amount == Float.MIN_VALUE) {
            presenter.prepareFailView("Incorrect amount! please ONLY enter number");
            return;
        }

        // Parse and validate the dates
        LocalDate localStartDate = parseDate(startDate);
        LocalDate localEndDate = parseDate(endDate);

        // null is returned or period is longer than the days between start and end
        if (localStartDate == null || localEndDate == null || localStartDate.isAfter(localEndDate)) {
            presenter.prepareFailView("Invalid date format or start date after end date! Please enter again in dd-MM-yyyy!");
            return;
        }

        // Validate and parse the period
        int customPeriod = validateAndParsePeriod(period);
        if (customPeriod == -1) {
            presenter.prepareFailView("The custom period is not in correct format or is 0, please enter again in dd-MM-yyyy!");
            return;
        }

        // Determine the ChronoUnit for the period
        ChronoUnit unit = getChronoUnit(period);
        if (!validatePeriod(unit, customPeriod, localStartDate, localEndDate)) {
            presenter.prepareFailView("Period is longer than the time between start and end date!");
            return;
        }

        boolean isInflow = amount >= 0.0;  // if amount < 0 then inflow = false
        processTransactions(isInflow, identification, amount, localStartDate, localEndDate, description, period, customPeriod, unit, category, currentDate);
    }

    /**
     * Validates and parses the transaction period.
     * <p>
     * This method checks if the period is one of the predefined period types (day, week, month, year).
     * If not, it tries to parse the period as an integer representing a custom period in days.
     * If parsing fails or the custom period is 0, it returns -1 as an indication of failure.
     * </p>
     *
     * @param period the transaction period as a string
     * @return the custom period as an integer, or 0 if it's a predefined period, or -1 if parsing fails
     */
    protected int validateAndParsePeriod(String period) {
        ArrayList<String> periodTypes = new ArrayList<>();
        periodTypes.add("day");
        periodTypes.add("week");
        periodTypes.add("month");
        periodTypes.add("year");

        if (periodTypes.contains(period)) {
            return 0;
        }

        try {
            int customPeriod = Integer.parseInt(period);
            if (customPeriod == 0) {
                return -1;
            }
            return customPeriod;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Returns the ChronoUnit corresponding to the given period string.
     * <p>
     * If the period is not one of the predefined types, it defaults to ChronoUnit.DAYS.
     * </p>
     *
     * @param period the transaction period as a string
     * @return the corresponding ChronoUnit
     */
    protected ChronoUnit getChronoUnit(String period) {
        switch (period) {
            case "day":
                return ChronoUnit.DAYS;
            case "week":
                return ChronoUnit.WEEKS;
            case "month":
                return ChronoUnit.MONTHS;
            case "year":
                return ChronoUnit.YEARS;
            default:
                return ChronoUnit.DAYS;
        }
    }

    /**
     * Validates if the period is shorter than the duration between the start and end dates.
     *
     * @param unit the ChronoUnit of the period
     * @param customPeriod the custom period in days
     * @param startDate the start date
     * @param endDate the end date
     * @return true if the period is valid, false otherwise
     */
    protected boolean validatePeriod(ChronoUnit unit, int customPeriod, LocalDate startDate, LocalDate endDate) {
        long totalDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

        // if the start day and end day is the same day
        if (totalDaysBetween == 0) {
            totalDaysBetween++;
        }

        if (unit != ChronoUnit.DAYS && totalDaysBetween < unit.getDuration().toDays()) {
            return false;
        } else if (totalDaysBetween < customPeriod) {
            return false;
        }

        return unit == ChronoUnit.DAYS || totalDaysBetween >= customPeriod;
    }

    /**
     * Processes the transactions based on whether they are inflow or outflow.
     * <p>
     * This method iterates over the period between the start and end dates, creating and saving
     * inflow or outflow transactions at each step. It updates the user account's balance and
     * interacts with the data access object to save the transactions.
     * </p>
     *
     * @param isInflow boolean indicating if the transaction is an inflow
     * @param identification the user's identification
     * @param amount the transaction amount
     * @param startDate the transaction start date
     * @param endDate the transaction end date
     * @param description the transaction description
     * @param period the transaction period
     * @param customPeriod the custom period in days
     * @param unit the ChronoUnit of the period
     * @param category the category of the transaction
     */
    private void processTransactions(boolean isInflow, String identification, float amount, LocalDate startDate,
                                     LocalDate endDate, String description, String period, int customPeriod,
                                     ChronoUnit unit, String category, LocalDate currentDate) {
        LocalDate date = startDate;
        P finalOutputData = null;

        // add transactions while from start to current date and not after end date
        while (!date.isAfter(currentDate) && !date.isAfter(endDate)) {
            if (isInflow) {
                finalOutputData = processInflowTransaction(identification, amount, startDate, description, endDate,
                        period, category, date);
            } else {
                finalOutputData = processOutflowTransaction(identification, amount, startDate, description, endDate,
                        period, category, date);
            }

            // update date
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
            presenter.prepareSuccessView(finalOutputData);
        }
    }

    /**
     * Processes an inflow transaction.
     * <p>
     * This method creates and saves a periodic inflow transaction, updates the user's total income
     * and balance, and interacts with the data access object to save the transaction.
     * </p>
     *
     * @param identification the user's identification
     * @param amount the transaction amount
     * @param startDate the current transaction date
     * @param description the transaction description
     * @param endDate the transaction end date
     * @param period the transaction period
     */
    protected P processInflowTransaction(String identification, float amount, LocalDate startDate, String description,
                                       LocalDate endDate, String period,
                                       String category, LocalDate transactionDate) {
        // Create a new PeriodicInflow object
        PeriodicInflow periodicInflow = new PeriodicInflow(identification, amount, startDate, description, endDate,
                period, category);

        // Update transaction date
        periodicInflow.setDate(transactionDate);

        // Create a new PeriodicInflow object
        float totalIncome = this.account.getTotalIncome() + amount;
        this.account.setTotalIncome(totalIncome);

        // Update the user's total income and balance
        float totalBalance = this.account.getTotalBalance() + amount;
        this.account.setTotalBalance(totalBalance);

        // Prepare the output data
        P outputData = this.createOutputData(periodicInflow);
        userDataAccessObject.saveTransaction(null, outputData, true);
        userDataAccessObject.update(account);

        return outputData;
    }

    /**
     * Processes an outflow transaction.
     * <p>
     * This method creates and saves a periodic outflow transaction, updates the user's total outflow
     * and balance, and interacts with the data access object to save the transaction.
     * </p>
     *
     * @param identification the user's identification
     * @param amount the transaction amount
     * @param currentDate the current transaction date
     * @param description the transaction description
     * @param endDate the transaction end date
     * @param period the transaction period
     */
    protected P processOutflowTransaction(String identification, float amount, LocalDate currentDate, String description,
                                                                               LocalDate endDate, String period, String category, LocalDate transactionDate) {
        // Create a new PeriodicOutflow object
        PeriodicOutflow periodicOutflow = new PeriodicOutflow(identification, amount, currentDate, description, endDate,
                period, category);

        // Update transaction date
        periodicOutflow.setDate(transactionDate);

        // Update the user's total outflow and balance
        float totalOutflow = account.getTotalOutflow() + amount;
        account.setTotalOutflow(totalOutflow);

        float totalBalance = account.getTotalBalance() + amount;
        account.setTotalBalance(totalBalance);

        // Prepare the output data
        P outputData = this.createOutputData(periodicOutflow);
        userDataAccessObject.saveTransaction(null, outputData, true);
        userDataAccessObject.update(account);

        return outputData;
    }

    /**
     * Creates output data for a given periodic transaction.
     * <p>
     * This method is intended to be overridden by subclasses to provide specific implementation details
     * for creating the output data.
     * </p>
     *
     * @param transaction the periodic transaction to create output data for
     * @return the output data for the periodic transaction
     */
    protected abstract P createOutputData(PeriodicTransaction transaction);

}
