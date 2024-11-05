package data_access.transaction;

import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory data access object (DAO) for handling one-time and periodic transactions for user accounts.
 * <p>
 * This class implements the {@link UserAccountDataAccessInterface} interface and provides an in-memory
 * implementation for managing user accounts and their associated transactions. It supports both one-time
 * and periodic transactions, though data is not persisted beyond the lifetime of the application.
 * </p>
 *
 * @author Xile
 * @author Eric
 */
public class InMemoryOneTimeDataAccessObject implements UserAccountDataAccessInterface {
    private static Map<String, UserAccount> users = new HashMap<>();
    private static Map<String, SharedAccountOneTimeTransactionOutputData> sharedAccountOneTimeTransactions = new HashMap<>();
    private static Map<String, SharedAccountPeriodicTransactionOutputData> sharedAccountPeriodicTransactions = new HashMap<>();

    /**
     * Constructs a new {@code InMemoryOneTimeDataAccessObject}.
     * <p>
     * This constructor initializes the in-memory storage for user accounts and transactions.
     * </p>
     */
    public InMemoryOneTimeDataAccessObject() {
        // Initialize the user map if needed
    }

    /**
     * Checks if a user account exists with the given identifier.
     *
     * @param identifier the unique identifier for the user account
     * @return {@code true} if a user account exists with the specified identifier; {@code false} otherwise
     */
    @Override
    public boolean existById(String identifier) {
        return users.containsKey(identifier);
    }

    /**
     * Retrieves a user account by its identification.
     *
     * @param identification the unique identifier for the user account
     * @return the user account with the specified identification
     */
    @Override
    public UserAccount getById(String identification) {
        return users.get(identification);
    }

    /**
     * Updates an existing user account in the in-memory data store.
     *
     * @param userAccount the user account with updated information
     */
    @Override
    public void update(UserAccount userAccount) {
        users.put(userAccount.getIdentification(), userAccount);
    }

    /**
     * Deletes a user account from the in-memory data store by its identification.
     *
     * @param id the unique identifier for the user account to be deleted
     */
    @Override
    public void deleteById(String id) {
        users.remove(id);
    }

    /**
     * Saves a new user account to the in-memory data store.
     *
     * @param userAccount the user account to be saved
     */
    @Override
    public void save(UserAccount userAccount) {
        users.put(userAccount.getIdentification(), userAccount);
    }

    /**
     * Saves a transaction for a user account. This method can handle both one-time and periodic transactions.
     *
     * @param oneTimeOutputData the one-time transaction data to be saved
     * @param periodicOutputData the periodic transaction data to be saved
     * @param isPeriodic true if the transaction is periodic, false if it is one-time
     */
    @Override
    public void saveTransaction(UserAccountOneTimeTransactionOutputData oneTimeOutputData,
                                UserAccountPeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {
        if (isPeriodic) {
            System.out.println("Saving periodic transaction data...");
        } else {
            System.out.println("Saving one-time transaction data...");
        }
    }

    /**
     * Reads transactions associated with a specific user ID.
     * <p>
     * This method retrieves all one-time transactions associated with the provided user ID.
     * Periodic transactions are excluded from the results.
     * </p>
     *
     * @param userId the unique identifier of the user whose transactions are to be retrieved
     * @return a list of transactions associated with the specified user ID, excluding periodic transactions
     */
    @Override
    public List<Transaction> readTransactions(String userId) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(users.get(userId).getTransactions());
        transactions.removeIf(transaction -> transaction instanceof PeriodicTransaction);
        return transactions;
    }

    /**
     * Retrieves a one-time transaction associated with a shared account by its transaction ID.
     *
     * @param transactionId the unique identifier for the transaction
     * @return the {@link SharedAccountOneTimeTransactionOutputData} associated with the specified transaction ID
     */
    public SharedAccountOneTimeTransactionOutputData getSharedOneTimeTransaction(String transactionId) {
        return sharedAccountOneTimeTransactions.get(transactionId);
    }

    /**
     * Retrieves a periodic transaction associated with a shared account by its transaction ID.
     *
     * @param transactionId the unique identifier for the transaction
     * @return the {@link SharedAccountPeriodicTransactionOutputData} associated with the specified transaction ID
     */
    public SharedAccountPeriodicTransactionOutputData getSharedPeriodicTransaction(String transactionId) {
        return sharedAccountPeriodicTransactions.get(transactionId);
    }
}




