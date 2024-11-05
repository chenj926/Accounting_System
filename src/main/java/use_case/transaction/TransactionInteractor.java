package use_case.transaction;

import data_access.account.AccountDataAccessInterface;
import entity.account.Account;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Abstract base class for handling transactions, including one-time and periodic transactions.
 *
 * @param <DAO> the type of data access interface for account data
 * @param <A> the type of account associated with the transaction
 * @param <O> the type of one-time transaction output data
 * @param <P> the type of periodic transaction output data
 *
 * @author Jessica
 * @author Eric
 */
public abstract class TransactionInteractor<
        DAO extends AccountDataAccessInterface<A, O, P>,
        A extends Account,
        O extends OneTimeTransactionOutputData,
        P extends PeriodicTransactionOutputData>{
    protected final DAO userDataAccessObject;
    protected A account;

    /**
     * Constructs a TransactionInteractor object with data access object,
     * and user account.
     *
     * @param accountDataAccessInterface the data access interface for user data
     * @param account the user account associated with the transaction
     */
    public TransactionInteractor(DAO accountDataAccessInterface,
                                 A account) {
        this.userDataAccessObject = accountDataAccessInterface;
        this.account = account;
    }

    /**
     * Checks if the provided user input is valid (not null or empty).
     *
     * @param userInfo the user input to check
     * @return true if the user input is valid, false otherwise
     */
    protected boolean checkValid(String userInfo) {
        if (userInfo == null || userInfo.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Parses and formats the transaction amount to two decimal places.
     * <p>
     * This method tries to parse the input string to a float and formats it to two decimal places.
     * If the parsing fails, it returns Float.MIN_VALUE as an indication of failure.
     * </p>
     *
     * @param stringAmount the transaction amount as a string
     * @return the parsed and formatted amount as a float, or Float.MIN_VALUE if parsing fails
     */
    protected float parseAmount(String stringAmount) {
        try {
            float amount = Float.parseFloat(stringAmount);
            return Float.parseFloat(String.format("%.2f", amount));
        } catch (NumberFormatException e) {
            return Float.MIN_VALUE; // Return a sentinel value indicating failure
        }
    }

    /**
     * Parses and validates the transaction date.
     * <p>
     * This method tries to parse the input date string to a LocalDate object using a strict date format.
     * If the parsing fails, it returns null as an indication of failure.
     * </p>
     *
     * @param date the transaction date as a string
     * @return the parsed date as a LocalDate object, or null if parsing fails
     */
    protected LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return null; // Return null indicating failure
        }
    }
}

