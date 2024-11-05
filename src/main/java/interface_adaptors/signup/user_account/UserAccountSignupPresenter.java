package interface_adaptors.signup.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.SignupPresenter;
import use_case.signup.user_account.UserAccountSignupOutputBoundary;
import use_case.signup.user_account.UserAccountSignupOutputData;

/**
 * The {@code UserAccountSignupPresenter} class implements the {@code UserAccountSignupOutputBoundary}
 * interface and handles the presentation logic for the user account signup process.
 * It updates the view model with the outcome of the signup process and manages view transitions.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that
 * the presentation logic is separated from the business logic and that the view accurately
 * reflects the current state of the application.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Xile Chen, Eric Chen
 * </p>
 */
public class UserAccountSignupPresenter extends SignupPresenter<
        UserAccountSignupViewModel,
        UserAccountSignupOutputData,
        UserAccountSignupState>
        implements UserAccountSignupOutputBoundary {

    /**
     * Constructs a {@code UserAccountSignupPresenter} object with the specified view manager model
     * and signup view model.
     *
     * @param viewManagerModel          the view manager model responsible for managing view transitions.
     * @param userAccountSignupViewModel the signup view model used to update the signup state.
     */
    public UserAccountSignupPresenter(ViewManagerModel viewManagerModel, UserAccountSignupViewModel userAccountSignupViewModel) {
        super(viewManagerModel, userAccountSignupViewModel);
    }

    /**
     * Prepares the success view with the given signup output data.
     * Updates the signup state to reflect a successful signup and navigates to the home page.
     *
     * @param data the signup output data containing user information and success status.
     */
    @Override
    public void prepareSuccessView(UserAccountSignupOutputData data) {
        if (!data.isUseCaseFailed()) {
            UserAccountSignupState userAccountSignupState = accountSignupViewModel.getState();
            userAccountSignupState.setSuccessMsg("User account signed up successfully.");
            accountSignupViewModel.setState(userAccountSignupState);
            accountSignupViewModel.firePropertyChanged();
        }

        // Navigate to the home page after successful signup
        viewManagerModel.changeView("home page");
    }
}
