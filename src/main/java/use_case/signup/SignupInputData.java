package use_case.signup;

/**
 * The SignupInputData class represents the input data required for the signup process.
 * It includes the identification and password fields that are used for signing up a user.
 *
 * @author Dana
 * @author Dana
 */
public abstract class SignupInputData {
    private String identification;
    private String password;

    /**
     * Constructs a SignupInputData object with the specified identification and password.
     *
     * @param password       the password for the signup
     * @param identification the identification for the signup
     */
    public SignupInputData(String password, String identification) {
        this.identification = identification;
        this.password = password;
    }

    /**
     * Returns the identification for the signup.
     *
     * @return the identification
     */
    public String getId() {
        return this.identification;
    }

    /**
     * Sets the identification for the signup.
     *
     * @param identification the new identification
     */
    public void setId(String identification) {
        this.identification = identification;
    }

    /**
     * Returns the password for the signup.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the signup.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
