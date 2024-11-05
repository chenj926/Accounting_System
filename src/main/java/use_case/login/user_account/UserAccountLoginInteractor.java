package use_case.login.user_account;

import entity.account.user_account.UserAccount;
import data_access.authentication.user_account.UserAccountLoginDataAccessInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import use_case.login.LoginInteractor;
import use_case.update_periodic_at_login.user_account.UserAccountUpdatePeriodicAtLoginInputData;

import java.time.LocalDate;

/**
 * The LoginInteractor class implements the LoginInputBoundary interface.
 * It handles the login process by validating the input data and interacting
 * with the data access layer and the presenter.
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
@Service
public class UserAccountLoginInteractor extends
        LoginInteractor<UserAccountLoginDataAccessInterface, UserAcountLoginOutputBoundary, UserAccountLoginOutputData, UserAccountLoginInputData, UserAccountLoginMediator>
        implements UserAccountLoginInputBoundary {

    /**
     * Constructs a LoginInteractor object with the specified data access interface
     * and output boundary.
     *
     * @param loginDAO  the data access interface for user data
     * @param presenter the output boundary for presenting the login results
     */
    @Autowired
    public UserAccountLoginInteractor(UserAccountLoginDataAccessInterface loginDAO,
            UserAcountLoginOutputBoundary presenter) {
        super(loginDAO, presenter);
    }

    /**
     * Executes the login process with the given input data.
     *
     * @param userAccountLoginInputData the input data required for the login
     *                                  process
     */
    @Override
    public void execute(UserAccountLoginInputData userAccountLoginInputData) {

        // check if username or password or id is valid (not empty)
        boolean validPassword = this.checkPassword(userAccountLoginInputData.getPassword());
        boolean validIdentificaiton = this.checkIdentification(userAccountLoginInputData.getIdentification());

        if (!validPassword && !validIdentificaiton) {
            presenter.prepareFailView("Identification AND Password can not be empty!");
        } else if (!validPassword) {
            presenter.prepareFailView("Password can not be empty!");
        } else if (!validIdentificaiton) {
            presenter.prepareFailView("Identification can not be empty!");
        } else {
            if (!accountDataAccessObject.existById(userAccountLoginInputData.getIdentification())) {
                // User does not exist
                presenter.prepareFailView("User not found");
                return;
            }

            // User exists, fetch the user account
            UserAccount userAccount = accountDataAccessObject.getById(userAccountLoginInputData.getIdentification());
            if (userAccount != null && !userAccount.getPassword().equals(userAccountLoginInputData.getPassword())) {
                presenter.prepareFailView("Incorrect Password!");
                return;
            }

            // attempt to login
            boolean isLogin = accountDataAccessObject.login(userAccount);

            // if login failed, password wrong
            if (!isLogin) {
                presenter.prepareFailView("Incorrect Password!");
            } else {
                // prepare output to presenter
                UserAccountLoginOutputData userAccountLoginOutputData = new UserAccountLoginOutputData(
                        userAccountLoginInputData.getIdentification(), true);
                presenter.prepareSuccessView(userAccountLoginOutputData);

                // Notify mediator on successful login
                UserAccountUpdatePeriodicAtLoginInputData userAccountUpdatePeriodicAtLoginInputData = new UserAccountUpdatePeriodicAtLoginInputData(
                        userAccountLoginInputData.getIdentification(), LocalDate.now());
                mediator.notifyLoginResult(true, userAccountUpdatePeriodicAtLoginInputData);
            }
        }
    }
}
