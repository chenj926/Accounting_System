package use_case.login;

/**
 * Input boundary interface for processing login requests.
 * <p>
 * This interface defines a method for executing the login use case with the provided input data.
 * Implementations of this interface are responsible for handling the input data and triggering the login logic.
 * </p>
 *
 * @param <T> the type of the input data required for the login use case
 *
 * @see LoginInputData
 * @see LoginInteractor
 *
 * @author
 */
public interface LoginInputBoundary<T> {

    /**
     * Executes the login use case with the provided input data.
     * <p>
     * This method processes the input data and triggers the logic to attempt a login.
     * </p>
     *
     * @param inputData the input data required for the login use case
     */
    void execute(T inputData);

}
