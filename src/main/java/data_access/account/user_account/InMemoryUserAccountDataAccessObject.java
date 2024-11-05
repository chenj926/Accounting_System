package data_access.account.user_account;

import data_access.authentication.user_account.UserSignupDataAccessInterface;
import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

import java.util.*;

import static java.lang.String.valueOf;

/**
 * In-memory data access object (DAO) for user account operations.
 * <p>
 * This class implements the {@link UserSignupDataAccessInterface} and {@link UserAccountDataAccessInterface} interface and provides an in-memory
 * implementation for managing user accounts. It is intended for testing purposes and does not persist data
 * beyond the lifetime of the application.
 * </p>
 *
 * @author Eric
 * @author Jessica
 */
public class InMemoryUserAccountDataAccessObject implements UserSignupDataAccessInterface, UserAccountDataAccessInterface{
    private final Map<String, UserAccount> users = new HashMap<>();
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Checks if a user exists with the given identification.
     *
     * @param identifier the unique identifier for the user account
     * @return {@code true} if a user account exists with the specified identification; {@code false} otherwise
     */
    @Override
    public boolean existById(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * Saves a new user account.
     *
     * @param newUser the {@link UserAccount} to be saved
     */
    public void save(UserAccount newUser) {
        users.put(newUser.getIdentification(), newUser);
    }

    /**
     * Saves a transaction for a user account. The transaction can be either a one-time or periodic transaction.
     * <p>
     * This method saves the transaction details associated with the user account. Depending on the value of
     * {@code isPeriodic}, either the one-time transaction data or periodic transaction data is saved.
     * </p>
     *
     * @param oneTimeOutputData the output data of the one-time transaction to be saved
     * @param periodicOutputData the output data of the periodic transaction to be saved
     * @param isPeriodic a flag indicating if the transaction is periodic
     */
    @Override
    public void saveTransaction(UserAccountOneTimeTransactionOutputData oneTimeOutputData, UserAccountPeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {
        String id = oneTimeOutputData.getId();
        System.out.println("output id"+id);
        float amount = oneTimeOutputData.getTransactionAmount();
        String date = valueOf(oneTimeOutputData.getTransactionDate());
        String description = oneTimeOutputData.getTransactionDescription();
        String category = oneTimeOutputData.getTransactionCategory();
        String userInfo = String.format("%s,%.2f,%s,%s,%s", id, amount, date, description, category);
    }

    public void saveTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    /**
     * Reads transactions associated with a specific user ID.
     * <p>
     * This method retrieves all transactions associated with the provided user ID. The transactions could
     * be of any type (e.g., one-time, periodic).
     * </p>
     *
     * @param userId the unique identifier of the user whose transactions are to be retrieved
     * @return a list of transactions associated with the specified user ID
     */
    @Override
    public List<Transaction> readTransactions(String userId) {
        return transactions;
    }

    /**
     * Deletes a user account by its unique identification.
     *
     * @param identifier the unique identifier for the user account to be deleted
     */
    public void deleteById(String identifier) {
        users.remove(identifier);
    }

    /**
     * Retrieves a user account by its unique identification.
     *
     * @param identifier the unique identifier for the user account
     * @return the {@link UserAccount} associated with the specified identification, or {@code null} if not found
     */
    public UserAccount getById(String identifier) {
        return users.get(identifier);
    }

    /**
     * Updates an existing user account in the data store.
     * <p>
     * This method checks if a user account with the given identification exists in the data store.
     * If it exists, the account is updated with the new data. If the account does not exist, an
     * {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param account the {@link UserAccount} object containing updated information
     * @throws IllegalArgumentException if the user account does not exist
     */
    @Override
    public void update(UserAccount account) {
            // Check if the account exists before updating
        if (users.containsKey(account.getIdentification())) {
            users.put(account.getIdentification(), account);
        } else {
            // Handle the case where the account doesn't exist, if needed
            throw new IllegalArgumentException("User account does not exist: " + account.getIdentification());
        }
    }

    /**
     * Retrieves all user accounts.
     *
     * @return a {@link Map} of all user accounts where the key is the user's identification and the value is the {@link UserAccount}
     */
    public Map<String, UserAccount> getAllUserAcc() {
        return users;
    }
}