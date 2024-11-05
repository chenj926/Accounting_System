package interface_adaptors.homepage.user_account;

import interface_adaptors.homepage.HomepageTwoController;
import use_case.homepage.user_account.UserAccountHomepageTwoInputBoundary;
import use_case.homepage.user_account.UserAccountHomepageTwoInputData;

/**
 * The {@code UserAccountHomepageTwoController} class is responsible for handling user interactions
 * and coordinating the flow of data between the user account homepage view and the associated use case interactor.
 * This class extends {@link HomepageTwoController} and is specifically designed for managing the user account homepage.
 *
 * <p>It creates input data required by the use case interactor to generate the necessary output for the view model.</p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class UserAccountHomepageTwoController extends HomepageTwoController<
        UserAccountHomepageTwoInputBoundary,
        UserAccountHomepageTwoViewModel,
        UserAccountHomepageTwoInputData,
        UserAccountHomepageTwoState> {

    /**
     * Constructs a {@code UserAccountHomepageTwoController} with the specified input boundary and view model.
     *
     * @param userAccountHomepageTwoInputBoundary the use case interactor for handling user account homepage data
     * @param viewModel the view model associated with the user account homepage
     */
    public UserAccountHomepageTwoController(UserAccountHomepageTwoInputBoundary userAccountHomepageTwoInputBoundary, UserAccountHomepageTwoViewModel viewModel) {
        super(userAccountHomepageTwoInputBoundary, viewModel);
    }

    /**
     * Creates the input data required by the use case interactor based on the provided user ID.
     *
     * @param id the user ID for which the input data is to be created
     * @return a new instance of {@link UserAccountHomepageTwoInputData} containing the user ID
     */
    @Override
    protected UserAccountHomepageTwoInputData createInputData(String id) {
        return new UserAccountHomepageTwoInputData(id);
    }

}
