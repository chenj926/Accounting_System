package use_case.signup;

/**
 * The SignupInputBoundary interface defines the contract for the signup use case.
 * It ensures that any class implementing this interface will provide the logic
 * to handle the signup process based on the provided input data.
 *
 * @param <T> the type of input data that extends {@link SignupInputData}
 *
 * @author Xile
 * @author Eric
 */
public interface SignupInputBoundary<T extends SignupInputData> {

    /**
     * Executes the signup process with the given input data.
     *
     * @param inputData the input data required for the signup process
     */
    void execute(T inputData);
}
