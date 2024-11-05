package use_case.transaction.one_time;

import data_access.account.AccountDataAccessInterface;
import entity.account.Account;
import entity.transaction.one_time.OneTimeInflow;
import entity.transaction.one_time.OneTimeOutflow;
import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.TransactionInteractor;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.time.LocalDate;

/**
 * Abstract class for handling one-time transactions. This class defines the common methods
 * for processing inflow and outflow transactions for different account types.
 *
 * @param <A> The type of account.
 * @param <O> The type of one-time transaction output data.
 *
 * @author Eric
 */
public abstract class OneTimeTransactionInteractor<
        DAO extends AccountDataAccessInterface<A, O, P>,
        A extends Account,
        O extends OneTimeTransactionOutputData,
        P extends PeriodicTransactionOutputData,
        I extends OneTimeTransactionInputData> extends TransactionInteractor<DAO, A, O, P> implements OneTimeTransactionInputBoundary<I> {

    protected final OneTimeTransactionOutputBoundary<O> presenter;

    /**
     * Constructs an AbstractOneTimeTransactionInteractor object.
     *
     * @param dataAccessInterface the data access interface for account data
     * @param presenter           the output boundary for presenting the one-time transaction results
     * @param account             the account associated with the transaction
     */
    protected OneTimeTransactionInteractor(DAO dataAccessInterface,
                                           OneTimeTransactionOutputBoundary<O> presenter,
                                           A account) {
        super(dataAccessInterface, account);
        this.presenter = presenter;
    }

    /**
     * Executes the one-time transaction based on the provided input data.
     * <p>
     * This method validates the input data, parses the transaction amount and date, and then
     * determines whether the transaction is an inflow or outflow. Depending on the type of transaction,
     * it delegates the processing to either {@link #processInflow(String, float, LocalDate, String, String)}
     * or {@link #processOutflow(String, float, LocalDate, String, String)}.
     * </p>
     *
     * @param inputData the input data for the one-time transaction, which includes details such as the transaction amount,
     *                  date, description, and category
     */
    @Override
    public void execute(I inputData) {
        String identification = inputData.getId();
        String stringAmount = inputData.getTransactionAmount();
        String date = inputData.getTransactionDate();
        String description = inputData.getTransactionDescription();
        String category = inputData.getTransactionCategory();

        if (!checkValid(stringAmount) || !checkValid(date) || !checkValid(description) || !checkValid(category)) {
            presenter.prepareFailView("Please do NOT have any empty inputs!");
            return;
        }

        float amount = parseAmount(stringAmount);
        if (amount == Float.MIN_VALUE) {
            presenter.prepareFailView("Incorrect amount! Please ONLY enter numbers.");
            return;
        }

        LocalDate localDate = parseDate(date);
        if (localDate == null) {
            presenter.prepareFailView("Invalid date format! Please enter again.");
            return;
        }

        boolean isInflow = amount >= 0.0;

        if (isInflow) {
            processInflow(identification, amount, localDate, description, category);
        } else {
            processOutflow(identification, amount, localDate, description, category);
        }
    }

    /**
     * Processes an inflow transaction.
     * <p>
     * This method updates the account's total income and balance, creates an {@link OneTimeInflow} object,
     * saves the transaction, and updates the account information in the database.
     * </p>
     *
     * @param identification the unique identifier of the transaction
     * @param amount the amount of the inflow transaction
     * @param date the date of the transaction
     * @param description the description of the transaction
     * @param category the category of the transaction
     */
    protected void processInflow(String identification, float amount, LocalDate date, String description, String category) {
        float totalIncome = this.account.getTotalIncome() + amount;
        this.account.setTotalIncome(totalIncome);  // update the total income

        // prepare inflow
        OneTimeInflow oneTimeInflow = new OneTimeInflow(identification, amount, date, description, category);
        float totalBalance = this.account.getTotalBalance() + amount;
        this.account.setTotalBalance(totalBalance);  // Update the balance accordingly

        // Prepare output data
        O outputData = createOutputData(oneTimeInflow);

        // Save this transaction
        userDataAccessObject.saveTransaction(outputData, null,false);
        // update the transaction info to user acc database as well
        userDataAccessObject.update(this.account);
        presenter.prepareSuccessView(outputData);
    }

    /**
     * Processes an outflow transaction.
     * <p>
     * This method updates the account's total outflow and balance, creates an {@link OneTimeOutflow} object,
     * saves the transaction, and updates the account information in the database.
     * </p>
     *
     * @param identification the unique identifier of the transaction
     * @param amount the amount of the outflow transaction
     * @param date the date of the transaction
     * @param description the description of the transaction
     * @param category the category of the transaction
     */
    protected void processOutflow(String identification, float amount, LocalDate date, String description, String category) {
        float totalOutflow = this.account.getTotalOutflow() + amount;
        this.account.setTotalOutflow(totalOutflow);  // update the total outflow

        // prepare outflow
        OneTimeOutflow oneTimeOutflow = new OneTimeOutflow(identification, amount, date, description, category);
        float totalBalance = this.account.getTotalBalance() + amount;
        this.account.setTotalBalance(totalBalance);  // Update the balance accordingly

        // Prepare output data
        O outputData = createOutputData(oneTimeOutflow);

        // Save this transaction
        userDataAccessObject.saveTransaction(outputData, null, false);
        // update the transaction info to user acc database as well
        userDataAccessObject.update(this.account);
        presenter.prepareSuccessView(outputData);
    }

    /**
     * Creates output data for a one-time transaction.
     * <p>
     * This method should be implemented by subclasses to create the appropriate output data for the specific
     * one-time transaction type.
     * </p>
     *
     * @param transaction the one-time transaction to create output data for
     * @return the output data for the one-time transaction
     */
    protected abstract O createOutputData(OneTimeTransaction transaction);

}
