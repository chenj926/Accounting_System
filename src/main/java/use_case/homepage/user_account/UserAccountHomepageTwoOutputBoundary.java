package use_case.homepage.user_account;

import use_case.homepage.HomepageTwoOutputBoundary;

/**
 * Output boundary interface for presenting the second homepage view specifically for user accounts.
 * <p>
 * This interface extends {@link HomepageTwoOutputBoundary} and specifies the output type as
 * {@link UserAccountHomepageTwoOutputData}. It defines methods for preparing views in the case of successful
 * or failed operations related to the user account homepage view.
 * </p>
 *
 * @see UserAccountHomepageTwoOutputData
 * @see HomepageTwoOutputBoundary
 *
 * @author Eric
 */
public interface UserAccountHomepageTwoOutputBoundary extends HomepageTwoOutputBoundary<UserAccountHomepageTwoOutputData> {

    /**
     * Prepares the view for a successful operation related to the user account homepage view.
     * <p>
     * This method is called when the operation has completed successfully. It prepares the output data
     * for presentation.
     * </p>
     *
     * @param userAccountHomepageTwoOutputData the output data for the user account homepage view
     */
    void prepareSuccessView(UserAccountHomepageTwoOutputData userAccountHomepageTwoOutputData);
}
