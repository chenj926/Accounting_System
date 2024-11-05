package interface_adaptors.signup.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.SignupPresenter;
import use_case.signup.shared_account.SharedAccountSignupOutputBoundary;
import use_case.signup.shared_account.SharedAccountSignupOutputData;

/**
 * The {@code SharedAccountSignupPresenter} class handles the presentation logic
 * specific to shared account signups, extending the functionality of {@code SignupPresenter}.
 * It updates the shared account signup state and manages view transitions after the
 * signup process is completed.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring
 * that the user interface reflects the outcome of the shared account signup process
 * and navigates the user to the appropriate view.
 * </p>
 *
 * <p>
 * <b>Author:</b> Xile Chen, Jessica Chen
 * </p>
 */
public class SharedAccountSignupPresenter extends SignupPresenter<
        SharedAccountSignupViewModel,
        SharedAccountSignupOutputData,
        SharedAccountSignupState>
        implements SharedAccountSignupOutputBoundary{

    /**
     * Constructs a {@code SharedAccountSignupPresenter} with the specified view manager model
     * and shared account signup view model.
     *
     * @param viewManagerModel             the view manager model responsible for managing view transitions.
     * @param sharedAccountSignupViewModel the shared account signup view model used to update the shared account signup state.
     */
    public SharedAccountSignupPresenter(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedAccountSignupViewModel) {// Call parent constructor
        super(viewManagerModel, sharedAccountSignupViewModel);
    }

    /**
     * Prepares the success view specifically for shared account signup.
     * Updates the shared account signup state to reflect a successful signup and navigates
     * the user to the home page.
     *
     * @param data the shared account signup output data containing the outcome of the signup process.
     */
    @Override
    public void prepareSuccessView(SharedAccountSignupOutputData data) {
        SharedAccountSignupState signupState = accountSignupViewModel.getState();
        signupState.setSuccessMsg("Shared account signed up successfully.");
        accountSignupViewModel.firePropertyChanged();

        // Navigate to the home page after successful signup
        viewManagerModel.changeView("home page");
    }
}
