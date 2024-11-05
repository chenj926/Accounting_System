package data_access.authentication.user_account;

import entity.account.user_account.UserAccount;
import org.springframework.stereotype.Service;

/**
 * Interface for data access operations related to user login functionality.
 * <p>
 * This interface provides methods to check if a user account exists, retrieve a user account by its identification,
 * and perform login operations.
 * </p>
 *
 * @author Dana
 * @author Jessica
 */
public interface UserAccountLoginDataAccessInterface {

    /**
     * Checks if a user account exists for the given identification.
     *
     * @param identification the unique identifier for the user account
     * @return {@code true} if a user account exists with the specified identification; {@code false} otherwise
     */
    boolean existById(String identification);

    /**
     * Retrieves a user account by its unique identification.
     *
     * @param identification the unique identifier for the user account
     * @return the UserAccount associated with the specified identification
     */
    UserAccount getById(String identification);

    /**
     * Performs login operations for the specified user account.
     *
     * @param userAccount the UserAccount to be logged in
     * @return {@code true} if the login is successful; {@code false} otherwise
     */
    boolean login(UserAccount userAccount);
}