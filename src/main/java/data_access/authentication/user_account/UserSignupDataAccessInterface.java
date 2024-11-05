package data_access.authentication.user_account;

import data_access.authentication.AccountSignupDataAccessInterface;
import entity.account.user_account.UserAccount;

/**
 * Interface for data access operations related to user account signup.
 * <p>
 * This interface provides methods to check the existence of a user by their identification
 * and to save a new user account into the data store.
 * </p>
 *
 * @author Eric
 */
public interface UserSignupDataAccessInterface extends AccountSignupDataAccessInterface<UserAccount> {

}