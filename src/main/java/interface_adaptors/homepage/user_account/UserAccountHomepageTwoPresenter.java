package interface_adaptors.homepage.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.homepage.HomepageTwoPresenter;
import use_case.homepage.user_account.UserAccountHomepageTwoOutputBoundary;
import use_case.homepage.user_account.UserAccountHomepageTwoOutputData;

/**
 * The {@code UserAccountHomepageTwoPresenter} class is responsible for presenting the output data for the user account homepage.
 * It updates the associated view model and view manager with the basic user information provided by the output data.
 *
 * <p>This class implements the {@link UserAccountHomepageTwoOutputBoundary} interface and extends the {@link HomepageTwoPresenter} class
 * to adhere to the principles of Clean Architecture.</p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class UserAccountHomepageTwoPresenter extends HomepageTwoPresenter<
        UserAccountHomepageTwoOutputData,
        UserAccountHomepageTwoViewModel,
        UserAccountHomepageTwoState> implements UserAccountHomepageTwoOutputBoundary {

    /**
     * Constructs a new {@code UserAccountHomepageTwoPresenter} with the specified view model and view manager.
     *
     * @param viewModel the view model to be updated with the output data
     * @param viewManager the view manager responsible for managing view transitions and updates
     */
    public UserAccountHomepageTwoPresenter(UserAccountHomepageTwoViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }
}
