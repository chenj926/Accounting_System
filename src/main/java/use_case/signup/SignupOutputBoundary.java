package use_case.signup;

/**
 * The SignupOutputBoundary interface defines the contract for presenting the results of the signup process.
 * It ensures that any class implementing this interface will handle the display of both successful and failed
 * signup operations.
 *
 * @param <T> the type of output data to be presented on successful signup
 *
 * @author Eric
 */
public interface SignupOutputBoundary<T extends SignupOutputData> {

    /**
     * Prepares the success view with the provided user data.
     *
     * @param user the data to be presented upon a successful signup
     */
    void prepareSuccessView(T user);

    /**
     * Prepares the fail view with the provided error message.
     *
     * @param error the error message to be displayed upon a failed signup
     */
    void prepareFailView(String error);
}
