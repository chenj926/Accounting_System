package data_access.authentication;

import entity.account.Account;

/**
 * Interface for data access operations related to account signup.
 * <p>
 * This interface provides methods to check the existence of a account by their identification
 * and to save a new account into the data store.
 * </p>
 *
 * @author Jessica
 */
public interface AccountSignupDataAccessInterface<A extends Account> {

    /**
     * Checks whether a shared account with the specified identification already exists.
     *
     * @param identification the unique identifier for the account
     * @return true if an account with the specified identification exists, false otherwise
     */
    boolean existById(String identification);

    A getById(String identification);

    /**
     * Saves a new account to the data store.
     *
     * @param user the user account to be saved
     */
    void save(A user);
}
