package interface_adaptors.login.user_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.LoginPresenter;
import use_case.login.user_account.UserAcountLoginOutputBoundary;
import use_case.login.user_account.UserAccountLoginOutputData;

/**
 * The {@code UserAccountLoginPresenter} class is responsible for handling the presentation logic
 * of the user account login process. It updates the view model based on the output data from the
 * use case interactor and manages view transitions within the application.
 *
 * <p>This class implements the {@code UserAcountLoginOutputBoundary} interface to ensure the
 * correct interaction between the use case layer and the interface adapters layer, following
 * clean architecture principles.</p>
 *
 * <p><b>Authors:</b> Jessica Chen, Eric Chen</p>
 */
public class UserAccountLoginPresenter extends LoginPresenter<UserAccountLoginViewModel,
        UserAccountLoginOutputData,
        UserAccountLoginState> implements UserAcountLoginOutputBoundary {

    /**
     * Constructs a {@code UserAccountLoginPresenter} object with the specified view manager model
     * and user account login view model.
     *
     * @param viewManagerModel the view manager model responsible for managing view transitions
     * @param userAccountLoginViewModel the login view model to update the login state
     */
    public UserAccountLoginPresenter(ViewManagerModel viewManagerModel,
                                     UserAccountLoginViewModel userAccountLoginViewModel){
        super(viewManagerModel, userAccountLoginViewModel);
    }

    /**
     * Prepares the success view with the given login output data.
     * Updates the login state with the user's identification and triggers a view transition to the homepage.
     *
     * @param userInfo the login output data containing user information and success status
     */
//    @Override
//    public void prepareSuccessView(UserAccountLoginOutputData userInfo){
//        // update the current login state
//        UserAccountLoginState userAccountLoginState = this.accountLoginViewModel.getState();
//        userAccountLoginState.setIdentification(userInfo.getIdentification());
//
//        // Set user ID in the view manager and update the view model's state
//        this.viewManagerModel.setUserId(userInfo.getIdentification());
//        this.accountLoginViewModel.setState(userAccountLoginState);
//        userAccountLoginState.setSuccessMsg("Successfully Logged In!!!");
//        this.accountLoginViewModel.firePropertyChanged();
//        this.viewManagerModel.setActiveViewName(this.accountLoginViewModel.getViewName());
//
//        // should change to next view
//        this.viewManagerModel.changeView("Homepage Two");
//    }

    @Override
    public void prepareSuccessView(UserAccountLoginOutputData userInfo){
        this.success = true;
        this.message = "Login successful";
        this.outputData = userInfo;
    }

    @Override
    public void prepareFailView(String errorMessage) {
        super.prepareFailView(errorMessage);
    }


}
