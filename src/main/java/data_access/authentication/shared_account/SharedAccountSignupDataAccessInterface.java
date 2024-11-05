package data_access.authentication.shared_account;

import data_access.authentication.AccountSignupDataAccessInterface;
import entity.account.shared_account.SharedAccount;

/**
 * Interface for data access operations related to shared account signup.
 * <p>
 * This interface provides methods to check the existence of a user by their identification
 * and to save a new shared account into the data store.
 * </p>
 *
 * @author Jessica
 */
public interface SharedAccountSignupDataAccessInterface extends AccountSignupDataAccessInterface<SharedAccount> {
}