package use_case.homepage;

/**
 * Input boundary interface for processing the second homepage view use case.
 * <p>
 * This interface defines a method for executing the homepage use case logic with the provided input data.
 * Implementations of this interface are responsible for handling the input data and triggering the appropriate
 * interactor logic.
 * </p>
 *
 * @param <I> the type of the input data required for the homepage use case
 *
 * @see HomepageTwoInputData
 * @see HomepageTwoInteractor
 *
 * @author Eric
 */
public interface HomepageTwoInputBoundary<I> {

    /**
     * Executes the homepage use case logic with the provided input data.
     * <p>
     * This method processes the input data and triggers the logic associated with the second homepage view.
     * </p>
     *
     * @param inputData the input data required for the homepage use case
     */
    void execute(I inputData);
}
