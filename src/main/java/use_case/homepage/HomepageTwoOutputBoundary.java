package use_case.homepage;

/**
 * Output boundary interface for presenting the second homepage view.
 * <p>
 * This interface defines methods for preparing views in the case of successful or failed operations
 * related to the second homepage view. Implementations of this interface are responsible for handling
 * the output data of the homepage use case.
 * </p>
 *
 * @param <O> the type of the output data for the homepage view
 *
 * @see HomepageTwoOutputData
 * @see HomepageTwoInteractor
 *
 * @author Eric
 */
public interface HomepageTwoOutputBoundary<O> {

    /**
     * Prepares the view for a successful operation related to the second homepage view.
     * <p>
     * This method is called when the operation has completed successfully. It prepares the output data
     * for presentation.
     * </p>
     *
     * @param userAccountHomepageTwoOutputData the output data for the homepage view
     */
    void prepareSuccessView(O userAccountHomepageTwoOutputData);

    /**
     * Prepares the view for a failed operation related to the second homepage view.
     * <p>
     * This method is called when there is an error in the operation. It prepares the error message
     * for presentation.
     * </p>
     *
     * @param err the error message describing the failure
     */
    void prepareFailView(String err);
}
