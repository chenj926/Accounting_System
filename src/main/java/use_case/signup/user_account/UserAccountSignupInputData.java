package use_case.signup.user_account;

import use_case.signup.SignupInputData;

/**
 * The UserAccountSignupInputData class represents the base input data required for a signup operation.
 * It includes the username, password, and identification of the user.
 *
 * @author Eric
 * @author Dana
 * @author Jessica
 */
public class UserAccountSignupInputData extends SignupInputData {
    private final String username;

    /**
     * Constructs a UserAccountSignupInputData object with the specified username, password, and identification.
     *
     * @param username       the username of the user
     * @param password       the password of the user
     * @param identification the identification of the user
     */
    public UserAccountSignupInputData(String username, String password, String identification) {
        super(password, identification);
        this.username = username;
    }

    /**
     * Gets the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return this.username;
    }
}
