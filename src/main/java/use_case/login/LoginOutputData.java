package use_case.login;

/**
 * Data class for outputting information after a login attempt.
 * <p>
 * This class stores the results of a login attempt, including the user's identification and whether the login was successful.
 * It provides methods to access this information.
 * </p>
 *
 * @see LoginInteractor
 * @see LoginInputBoundary
 *
 * @author Dana
 * @author Jessica
 */
public class LoginOutputData {
    private String identification;
    private boolean success;

    /**
     * Constructs a new {@code LoginOutputData} with the specified identification and success status.
     *
     * @param identification the user's identification
     * @param success {@code true} if the login was successful; {@code false} otherwise
     */
    public LoginOutputData(String identification, boolean success) {
        this.identification = identification;
        this.success = success;
    }

    /**
     * Retrieves the user's identification.
     *
     * @return the user's identification as a string
     */
    public String getIdentification() {return this.identification;}

    /**
     * Checks if the login was successful.
     *
     * @return {@code true} if the login was successful; {@code false} otherwise
     */
    public boolean isSuccess() {return this.success;}
}
