package use_case.login;

/**
 * Data class for inputting information into the login use case.
 * <p>
 * This abstract class stores the necessary input data for attempting a login, including the user's identification
 * and password. It provides methods to get and set this information.
 * </p>
 *
 * @see LoginInputBoundary
 * @see LoginInteractor
 *
 * @author Dana
 */
public abstract class LoginInputData {
    private String identification;
    private String password;

    /**
     * Constructs a new {@code LoginInputData} with the specified identification and password.
     *
     * @param identification the user's identification
     * @param password the user's password
     */
    public LoginInputData(String identification, String password) {
        this.identification = identification;
        this.password = password;
    }

    /**
     * Retrieves the user's identification.
     *
     * @return the user's identification as a string
     */
    public String getIdentification() {return this.identification;}

    /**
     * Sets the user's identification.
     *
     * @param identification the user's identification to set
     */
    public void setIdentification(String identification) {this.identification = identification;}

    /**
     * Retrieves the user's password.
     *
     * @return the user's password as a string
     */
    public String getPassword() {return this.password;}

    /**
     * Sets the user's password.
     *
     * @param password the user's password to set
     */
    public void setPassword(String password) {this.password = password;}
}
