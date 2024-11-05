//package interface_adaptors.login.user_account;
//
//import interface_adaptors.ViewManagerModel;
//import interface_adaptors.login.LoginPresenter;
//import use_case.login.LoginOutputData;
//import use_case.login.user_account.UserAccountLoginOutputBoundaryTemp;
//import use_case.login.user_account.UserAccountLoginOutputData;
//
//public class UserAccountLoginPresenterTemp extends LoginPresenter<UserAccountLoginViewModel,
//        UserAccountLoginOutputData,
//        UserAccountLoginState>  implements UserAccountLoginOutputBoundaryTemp {
//
//    /**
//     * Constructs a {@code UserAccountLoginPresenter} object with the specified view manager model
//     * and user account login view model.
//     *
//     * @param viewManagerModel the view manager model responsible for managing view transitions
//     * @param userAccountLoginViewModel the login view model to update the login state
//     */
//    public UserAccountLoginPresenterTemp(){
//        super(viewManagerModel, userAccountLoginViewModel);
//    }
//
//    /**
//     * Prepares the success view with the given login output data.
//     * Updates the login state with the user's identification and triggers a view transition to the homepage.
//     *
//     * @param userInfo the login output data containing user information and success status
//     */
////    @Override
////    public void prepareSuccessView(UserAccountLoginOutputData userInfo){
////        // update the current login state
////        UserAccountLoginState userAccountLoginState = this.accountLoginViewModel.getState();
////        userAccountLoginState.setIdentification(userInfo.getIdentification());
////
////        // Set user ID in the view manager and update the view model's state
////        this.viewManagerModel.setUserId(userInfo.getIdentification());
////        this.accountLoginViewModel.setState(userAccountLoginState);
////        userAccountLoginState.setSuccessMsg("Successfully Logged In!!!");
////        this.accountLoginViewModel.firePropertyChanged();
////        this.viewManagerModel.setActiveViewName(this.accountLoginViewModel.getViewName());
////
////        // should change to next view
////        this.viewManagerModel.changeView("Homepage Two");
////    }
//
//    @Override
//    public void prepareSuccessView(UserAccountLoginOutputData userInfo){
//        this.success = true;
//        this.message = "Login successful";
//        this.outputData = userInfo;
//    }
//
//    @Override
//    public void prepareFailView(String errorMessage) {
//        super.prepareFailView(errorMessage);
//    }
//
//
//}
