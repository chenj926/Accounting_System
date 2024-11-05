package use_case.homepage.shared_account;

import use_case.homepage.HomepageTwoOutputBoundary;

/**
 * Output boundary interface for presenting the second homepage view specifically for shared accounts.
 * <p>
 * This interface extends {@link HomepageTwoOutputBoundary} and specifies the output type as
 * {@link SharedAccountHomepageTwoOutputData}. It defines methods for preparing views in the case of successful
 * or failed operations related to the shared account homepage view.
 * </p>
 *
 * @see SharedAccountHomepageTwoOutputData
 * @see HomepageTwoOutputBoundary
 *
 * @author Eric
 */
public interface SharedAccountHomepageTwoOutputBoundary extends HomepageTwoOutputBoundary<SharedAccountHomepageTwoOutputData> {

    /**
     * Prepares the view for a successful operation related to the shared account homepage view.
     * <p>
     * This method is called when the operation has completed successfully. It prepares the output data
     * for presentation.
     * </p>
     *
     * @param userAccountHomepageTwoOutputData the output data for the shared account homepage view
     */
    void prepareSuccessView(SharedAccountHomepageTwoOutputData userAccountHomepageTwoOutputData);
}
