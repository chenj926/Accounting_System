package data_access.authentication.user_account;
import entity.account.user_account.UserAccount;

/**
 * Interface for data access operations related to user logout functionality.
 * <p>
 * This interface provides methods for retrieving a user account by its identification
 * and handling the logout process for a user.
 * </p>
 *
 * @author Dana
 * @author Jessica
 */
public interface UserAccountLogoutDataAccessInterface {

    /**
     * Retrieves a user account by its unique identification.
     *
     * @param identification the unique identifier for the user account
     * @return the UserAccount associated with the specified identification
     */
    UserAccount getById(String identification);

    /**
     * Handles the logout process for a specified user account.
     *
     * @param user the UserAccount to be logged out
     */
    void logout(UserAccount user);
}
