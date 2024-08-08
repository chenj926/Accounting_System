package use_case.login;

import entity.account.UserAccount;
import data_access.authentication.LoginDataAccessInterface;
import use_case.update_periodic_at_login.UpdatePeriodicAtLoginInputData;

import java.time.LocalDate;

/**
 * The LoginInteractor class implements the LoginInputBoundary interface.
 * It handles the login process by validating the input data and interacting with the data access layer and the presenter.
 *
 * @author Dana
 * @author Eric
 */
public class UserAccountLoginInteractor implements UserAccountLoginInputBoundary {
    final LoginDataAccessInterface userDataAccessObject;
    final UserAcountLoginOutputBoundary presenter;
    private LoginMediator mediator;

    /**
     * Constructs a LoginInteractor object with the specified data access interface and output boundary.
     *
     * @param LoginDataAccessInterface the data access interface for user data
     * @param logInOutputBoundaryUser      the output boundary for presenting the login results
     */
    public UserAccountLoginInteractor(LoginDataAccessInterface LoginDataAccessInterface, UserAcountLoginOutputBoundary logInOutputBoundaryUser) {
        this.userDataAccessObject = LoginDataAccessInterface;
        this.presenter = logInOutputBoundaryUser;
    }

    /**
     * Sets the mediator for the interactor.
     *
     * @param mediator the LoginMediator instance to set
     */
    public void setMediator(LoginMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * Executes the login process with the given input data.
     *
     * @param userAccountLoginInputData the input data required for the login process
     */
    @Override
    public void execute(UserAccountLoginInputData userAccountLoginInputData) {

            // check if username or password or id is valid (not empty)
            boolean validPassword = this.checkPassword(userAccountLoginInputData.getPassword());
            boolean validIdentificaiton = this.checkIdentification(userAccountLoginInputData.getIdentification());

            if (!validPassword && !validIdentificaiton) {
                presenter.prepareFailView("Identification AND Password can not be empty!");
                return;
            } else if (!validPassword) {
                presenter.prepareFailView("Password can not be empty!");
                return;
            } else if (!validIdentificaiton) {
                presenter.prepareFailView("Identification can not be empty!");
                return;
            } else {
                if (!userDataAccessObject.existById(userAccountLoginInputData.getIdentification())) {
                    //User does not exist
                    presenter.prepareFailView("User not found");
                    return;
                }

                //User exists, fetch the user account
                UserAccount userAccount = userDataAccessObject.getById(userAccountLoginInputData.getIdentification());
                if (userAccount != null && !userAccount.getPassword().equals(userAccountLoginInputData.getPassword())) {
                    presenter.prepareFailView("Incorrect Password!");
                    return;
                }

                // attempt to login
                boolean isLogin = userDataAccessObject.login(userAccount);

                // if login failed, password wrong
                if (!isLogin) {
                    presenter.prepareFailView("Incorrect Password!");
                } else {
                    // prepare output to presenter
                    UserAccountLoginOutputData userAccountLoginOutputData = new UserAccountLoginOutputData(userAccountLoginInputData.getIdentification(), true);
                    presenter.prepareSuccessView(userAccountLoginOutputData);

                    // Notify mediator on successful login
                    UpdatePeriodicAtLoginInputData updatePeriodicAtLoginInputData = new UpdatePeriodicAtLoginInputData(userAccountLoginInputData.getIdentification(), LocalDate.now());
                    mediator.notifyLoginResult(true, updatePeriodicAtLoginInputData);
                    }
                }

        }

    /**
     * Checks if the provided password is valid (not null or empty).
     *
     * @param password the password to check
     * @return true if the password is valid, false otherwise
     */
    public boolean checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the provided identification is valid (not null or empty).
     *
     * @param id the identification to check
     * @return true if the identification is valid, false otherwise
     */
    public boolean checkIdentification(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return true;
    }

}
