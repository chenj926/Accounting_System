package use_case.login;

/**
 * Output boundary interface for presenting the results of a login attempt.
 * <p>
 * This interface defines methods for preparing views in the case of successful or failed login operations.
 * Implementations of this interface are responsible for handling the output data of the login use case.
 * </p>
 *
 * @param <O> the type of the output data for the login use case
 *
 * @see LoginOutputData
 * @see LoginInteractor
 *
 * @author Eric
 * @author Dana
 */
public interface LoginOutputBoundary<O> {

    /**
     * Prepares the view for a successful login attempt.
     * <p>
     * This method is called when the login operation completes successfully. It prepares the output data for presentation.
     * </p>
     *
     * @param user the output data containing information about the logged-in user
     */
    void prepareSuccessView(O user);

    /**
     * Prepares the view for a failed login attempt.
     * <p>
     * This method is called when the login operation fails. It prepares an error message for presentation.
     * </p>
     *
     * @param error the error message describing the reason for the failed login attempt
     */
    void prepareFailView(String error);
}
