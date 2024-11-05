package data_access.authentication.shared_account;

import data_access.account.shared_account.InMemorySharedAccountDataAccessObject;
import entity.account.shared_account.SharedAccount;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory data access object (DAO) for handling login operations for shared accounts.
 * <p>
 * This class extends {@link InMemorySharedAccountDataAccessObject} and implements {@link SharedAccountLoginDataAccessInterface}.
 * It provides functionality for managing shared account login states in-memory, making it suitable for testing purposes.
 * </p>
 *
 * @author Eric
 */
public class InMemorySharedAccountLoginDataAccessObject extends InMemorySharedAccountDataAccessObject implements SharedAccountLoginDataAccessInterface {
    private static Map<String, Boolean> sharedAccountUserLogin;

    /**
     * Constructs a new instance of {@code InMemorySharedAccountLoginDataAccessObject}.
     * This constructor initializes the in-memory map for tracking login states.
     */
    public InMemorySharedAccountLoginDataAccessObject() {
        this.sharedAccountUserLogin = new HashMap<>();
    }

    /**
     * Checks if a shared account exists by its identification.
     *
     * @param identification the unique identifier for the shared account
     * @return {@code true} if the account exists in the in-memory map; {@code false} otherwise
     */
    @Override
    public boolean existById(String identification) {
        return this.sharedAccountUserLogin.containsKey(identification);
    }

    /**
     * Logs in a user by verifying their identification.
     * <p>
     * If the account exists, it marks the account as logged in within the in-memory map.
     * </p>
     *
     * @param account the {@link SharedAccount} object containing the user's identification
     * @return {@code true} if the user is successfully logged in; {@code false} otherwise
     */
    @Override
    public boolean login(SharedAccount account) {
        String identifier = account.getIdentification();
        if (existById(identifier)) {
            this.sharedAccountUserLogin.put(identifier, true); // Mark the account as logged in
            return true;
        }
        return false;
    }

    /**
     * Retrieves a shared account by its identification from the in-memory data store.
     *
     * @param identification the unique identifier for the shared account
     * @return the {@link SharedAccount} associated with the specified identification
     */
    @Override
    public SharedAccount getById(String identification) {
        return super.getById(identification);
    }
}
