package interface_adaptors.login.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.LoginPresenter;
import use_case.login.shared_account.SharedAccountLoginOutputBoundary;
import use_case.login.shared_account.SharedAccountLoginOutputData;

/**
 * The {@code SharedAccountLoginPresenter} class handles the presentation logic
 * for shared account login, implementing the {@code SharedAccountLoginOutputBoundary}
 * interface. It updates the view model and manages view transitions upon successful
 * login.
 * <p>
 * This presenter is part of the presentation layer in the Clean Architecture, ensuring that
 * the presentation logic is separated from the core business logic. It interacts with the
 * view model to reflect the current state of the shared account login process and
 * coordinates the transition to the appropriate view.
 * </p>
 *
 * <p>
 * <b>Author:</b> Xile Chen, Jessica Chen, Eric Chen
 * </p>
 */
public class SharedAccountLoginPresenter extends LoginPresenter<
        SharedAccountLoginViewModel,
        SharedAccountLoginOutputData,
        SharedAccountLoginState> implements SharedAccountLoginOutputBoundary {

    /**
     * Constructs a {@code SharedAccountLoginPresenter} object with the specified view manager model
     * and shared account login view model.
     *
     * @param viewManagerModel            the view manager model responsible for managing view transitions.
     * @param sharedAccountLoginViewModel the shared account login view model that updates the shared account login state.
     */
    public SharedAccountLoginPresenter(ViewManagerModel viewManagerModel, SharedAccountLoginViewModel sharedAccountLoginViewModel) {
        super(viewManagerModel, sharedAccountLoginViewModel);
    }

    /**
     * Prepares the success view using the provided shared account login output data.
     * This method updates the shared account login state with the identification and success message,
     * triggers a property change event, and changes the active view to the shared account homepage.
     *
     * @param userInfo the login output data containing user information and success status.
     */
    @Override
    public void prepareSuccessView(SharedAccountLoginOutputData userInfo) {
            // Handle shared account login
            SharedAccountLoginState sharedAccountLoginState = this.accountLoginViewModel.getState();
            sharedAccountLoginState.setIdentification(userInfo.getIdentification());
            this.viewManagerModel.setUserId(userInfo.getIdentification());
            this.accountLoginViewModel.setState(sharedAccountLoginState);
            sharedAccountLoginState.setSuccessMsg("Successfully Logged In to Shared Account!!!");
            this.accountLoginViewModel.firePropertyChanged();
            this.viewManagerModel.setActiveViewName(this.accountLoginViewModel.getViewName());

            // Change to the next view
            this.viewManagerModel.changeView("Shared Account Homepage Two");
    }
}


