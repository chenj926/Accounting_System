package interface_adaptors.login;

import interface_adaptors.ViewManagerModel;
import use_case.login.LoginOutputData;

/**
 * The {@code LoginPresenter} class is an abstract base class responsible for handling the presentation
 * logic for login operations. It manages the interactions between the login view model and the view manager,
 * ensuring that the appropriate views are updated based on the outcome of the login process.
 *
 * <p>This class should be extended by specific implementations that provide concrete behavior for handling
 * login success and failure scenarios.</p>
 *
 * @param <V> the type of {@code LoginViewModel} that this presenter interacts with
 * @param <O> the type of {@code LoginOutputData} containing the output data from the login use case
 * @param <S> the type of {@code LoginState} representing the current state of the login process
 *
 * <p><b>Author:</b> Jessica Chen</p>
 */
public abstract class LoginPresenter<
        V extends LoginViewModel<S>,
        O extends LoginOutputData,
        S extends LoginState>{
    protected final ViewManagerModel viewManagerModel;
    protected final  V accountLoginViewModel;

    // Fields to store the login result
    protected boolean success;
    protected String message;
    protected O outputData;

    /**
     * Constructs a {@code LoginPresenter} with the specified view manager and login view model.
     *
     * @param viewManagerModel the view manager responsible for managing the application's views
     * @param accountLoginViewModel the login view model that holds the current state of the login process
     */
    public LoginPresenter(ViewManagerModel viewManagerModel, V accountLoginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.accountLoginViewModel = accountLoginViewModel;
    }

    /**
     * Prepares the success view with the given user information.
     * This method should be implemented by subclasses to handle the presentation of a successful login.
     *
     * @param userInfo the output data containing the user information after a successful login
     */
    public abstract void prepareSuccessView(O userInfo);

    /**
     * Prepares the fail view with the given error message.
     * Updates the login state with the error message and clears the success message.
     *
     * <p>This method is called when a login attempt fails, ensuring that the appropriate error message
     * is presented to the user and that any previous success message is cleared.</p>
     *
     * @param errorMessage the error message to be presented in case of a failed login attempt
     */
//    public void prepareFailView(String err){
//        S accountLoginState = this.accountLoginViewModel.getState();
//        accountLoginState.setStateError(err);
//        accountLoginState.setSuccessMsg(null); // Clear success message on failure
//        this.accountLoginViewModel.firePropertyChanged();
//
//    }

    public void prepareFailView(String errorMessage){
        this.success = false;
        this.message = errorMessage;
        this.outputData = null;
    }

    // Getters for the controller to access the login result
    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public O getOutputData() {
        return outputData;
    }


}
