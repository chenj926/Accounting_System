package entity;

/**
 * The AccountFactory class is responsible for creating different types of account objects.
 * It provides methods to create user accounts and shared accounts.
 *
 * @author Jessica Chen
 */
public class AccountFactory {

    /**
     * Creates a new UserAccount object with the specified username, password, and identification.
     *
     * @param username       the username for the new user account
     * @param password       the password for the new user account
     * @param identification the identification for the new user account
     * @return a new UserAccount object
     */
    public UserAccount createUserAccount(String username, String password, String identification) {
        return new UserAccount(username, password, identification);
    }

    /**
     * Creates a new SharedAccount object with the specified identification.
     *
     * @param identification the identification for the new shared account
     * @return a new SharedAccount object
     */
    public SharedAccount createSharedAccount(String identification) {
        return new SharedAccount(identification);
    }
}