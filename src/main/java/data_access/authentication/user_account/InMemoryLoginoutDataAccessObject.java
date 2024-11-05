package data_access.authentication.user_account;

import data_access.account.user_account.InMemoryUserAccountDataAccessObject;
import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

import java.util.*;
/**
 * In-memory data access object (DAO) for user login and logout operations.
 * <p>
 * This class implements both the {@link UserAccountLoginDataAccessInterface} and {@link UserAccountLogoutDataAccessInterface}
 * interfaces and provides an in-memory implementation for managing user login states. It is intended for
 * testing purposes and does not persist data beyond the lifetime of the application.
 * </p>
 *
 * @author Eric
 * @author Jessica
 */
public class InMemoryLoginoutDataAccessObject extends InMemoryUserAccountDataAccessObject implements UserAccountLogoutDataAccessInterface, UserAccountLoginDataAccessInterface {
    private static Map<String, Boolean> userLogin;

    /**
     * Constructs a new {@link InMemoryLoginoutDataAccessObject} with an empty user login map.
     */
    public InMemoryLoginoutDataAccessObject() {
        userLogin = new HashMap<>();
    }

    /**
     * Checks if a user account exists with the given identifier.
     *
     * @param identification the unique identifier for the user account
     * @return {@code true} if a user account exists with the specified identifier; {@code false} otherwise
     */
    @Override
    public boolean existById(String identification) {
        return userLogin.containsKey(identification);
    }

    /**
     * Logs in a user account, marking the account as logged in.
     *
     * @param userAccount the {@link UserAccount} to log in
     * @return {@code false} as this method is not yet implemented
     */
    @Override
    public boolean login(UserAccount userAccount) {
        userLogin.put(userAccount.getIdentification(), true);
        return false;
    }

    /**
     * Logs out a user account, marking the account as logged out.
     *
     * @param user the {@link UserAccount} to log out
     */
    @Override
    public void logout(UserAccount user) {
        userLogin.put(user.getIdentification(), false);
    }

    /**
     * Retrieves a user account by its unique identifier.
     *
     * @param identification the unique identifier for the user account
     * @return the {@link UserAccount} associated with the specified identifier
     */
    @Override
    public UserAccount getById(String identification) {
        return super.getById(identification);
    }

    /**
     * Updates an existing user account in the in-memory data store.
     * <p>
     * This method is intended to update the details of a user account. However, the implementation is currently missing.
     * </p>
     *
     * @param account the user account with updated information
     */
    @Override
    public void update(UserAccount account) {

    }

    /**
     * Saves a transaction for a user account. This method can handle both one-time and periodic transactions.
     * <p>
     * This method is intended to save transaction data to the in-memory store. However, the implementation is currently missing.
     * </p>
     *
     * @param oneTimeOutputData the one-time transaction data to be saved
     * @param periodicOutputData the periodic transaction data to be saved
     * @param isPeriodic true if the transaction is periodic, false if it is one-time
     */
    @Override
    public void saveTransaction(UserAccountOneTimeTransactionOutputData oneTimeOutputData, UserAccountPeriodicTransactionOutputData periodicOutputData, boolean isPeriodic) {

    }

    /**
     * Reads transactions associated with a specific user ID.
     * <p>
     * This method is intended to retrieve a list of transactions for a user account. However, the implementation is currently missing.
     * </p>
     *
     * @param userId the unique identifier of the user whose transactions are to be retrieved
     * @return a list of transactions associated with the specified user ID
     */
    @Override
    public List<Transaction> readTransactions(String userId) {
        return List.of();
    }
}
