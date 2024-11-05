package data_access.transaction;

import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import entity.transaction.one_time.OneTimeTransaction;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory data access object (DAO) for user account and transaction operations.
 * <p>
 * This class implements the {@link UserAccountDataAccessInterface} interface and provides an in-memory
 * implementation for managing user accounts and transactions. It is intended for testing purposes and does not
 * persist data beyond the lifetime of the application.
 * </p>
 *
 * @author Eric
 */
public class InMemoryPeriodicDataAccessObject implements UserAccountDataAccessInterface {
    private Map<String, UserAccount> users = new HashMap<>();

    /**
     * Constructs a new {@link InMemoryPeriodicDataAccessObject} with an empty user account map.
     */
    public InMemoryPeriodicDataAccessObject() {
        this.users = new HashMap<>();
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
     * Retrieves a user account by its unique identifier.
     *
     * @param identification the unique identifier for the user account
     * @return {@code null} as this method is not implemented yet
     */
    @Override
    public UserAccount getById(String identification) {
        return null;
    }

    /**
     * Updates a user account.
     *
     * @param userAccount the {@link UserAccount} to be updated
     */
    @Override
    public void update(UserAccount userAccount) {
        System.out.println("update");
    }

    /**
     * Deletes a user account by its unique identifier.
     *
     * @param id the unique identifier for the user account to be deleted
     */
    @Override
    public void deleteById(String id) {
        System.out.println("id");
    }

    /**
     * Saves a user account.
     *
     * @param userAccount the {@link UserAccount} to be saved
     */
    @Override
    public void save(UserAccount userAccount) {
        System.out.println("id");
    }

    /**
     * Saves transaction data. This method is not yet implemented.
     *
     * @param oneTimeOutputData   the output data for one-time transactions
     * @param periodicOutputData  the output data for periodic transactions
     * @param isPeriodic          {@code true} if the transaction is periodic; {@code false} if it is one-time
     */
    @Override
    public void saveTransaction(UserAccountOneTimeTransactionOutputData oneTimeOutputData,
                                UserAccountPeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {
        System.out.println("not implemented yet");
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
        transactions.removeIf(transaction -> transaction instanceof OneTimeTransaction);
        return transactions;
    }
}
