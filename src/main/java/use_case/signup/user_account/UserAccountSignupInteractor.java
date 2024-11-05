package use_case.signup.user_account;

import data_access.authentication.user_account.UserSignupDataAccessInterface;
import entity.account.user_account.UserAccount;
import entity.account.AccountFactory;
import use_case.signup.SignupInteractor;

import java.time.LocalDate;

/**
 * The UserAccountSignupInteractor class implements the UserAccountSignupInputBoundary interface.
 * It handles the signup process by validating the input data, interacting with the data access layer,
 * and using the presenter to prepare the output views.
 *
 * @author Eric
 * @author Dana
 * @author Jessica
 */
public class UserAccountSignupInteractor extends SignupInteractor<
        UserSignupDataAccessInterface,
        UserAccountSignupInputData> implements UserAccountSignupInputBoundary {
    private final UserAccountSignupOutputBoundary presenter;

    /**
     * Constructs a UserAccountSignupInteractor object with the specified data access interface, output boundary, and account factory.
     *
     * @param userSignupDataAccessInterface the data access interface for user data
     * @param userAccountSignupOutputBoundary          the output boundary for presenting the signup results
     * @param accountFactory                the factory for creating user accounts
     */
    public UserAccountSignupInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                                       UserAccountSignupOutputBoundary userAccountSignupOutputBoundary,
                                       AccountFactory accountFactory) {
        super(userSignupDataAccessInterface, accountFactory);
        this.presenter = userAccountSignupOutputBoundary;
    }

    /**
     * Executes the signup process with the given input data.
     *
     * @param userAccountSignupInputData the input data required for the signup process
     */
    @Override
    public void execute(UserAccountSignupInputData userAccountSignupInputData) {
        if (userDataAccessObject.existById(userAccountSignupInputData.getId())) {
            // user already exist, return back fail view to presenter
            presenter.prepareFailView("User already exist!!!");
        }
        else {
            // check if username or password or id is valid (not empty)
            boolean validUsername = this.checkUsername(userAccountSignupInputData.getUsername());
            boolean validPassword = this.checkPassword(userAccountSignupInputData.getPassword());
            boolean validIdentificaiton = this.checkIdentification(userAccountSignupInputData.getId());


            if (!validUsername && !validPassword && !validIdentificaiton) {
                presenter.prepareFailView("Username AND Password AND Identification can not be empty!");
            } else if (!validUsername && !validPassword) {
                presenter.prepareFailView("Username AND Password can not be empty!");
            } else if (!validUsername && !validIdentificaiton) {
                presenter.prepareFailView("Username AND Identification can not be empty!");
            } else if (!validPassword && !validIdentificaiton) {
                presenter.prepareFailView("Password AND Identification can not be empty!");
            } else if(!validUsername) {
                presenter.prepareFailView("Username can not be empty!");
            } else if (!validPassword){
                presenter.prepareFailView("Password can not be empty!");
            } else if (!validIdentificaiton) {
                presenter.prepareFailView("Identification can not be empty!");
            } else {
                // create new user
                UserAccount newUser = accountFactory.createUserAccount(userAccountSignupInputData.getUsername(),
                        userAccountSignupInputData.getPassword(), userAccountSignupInputData.getId());
                newUser.setLastLoginDate(LocalDate.now());
                userDataAccessObject.save(newUser);  // save this user

                // prepare output to presenter
                UserAccountSignupOutputData userAccountSignupOutputData = new UserAccountSignupOutputData(newUser.getUsername(), false);
                presenter.prepareSuccessView(userAccountSignupOutputData);
            }
        }
    }

    /**
     * Checks if the provided username is valid (not null or empty).
     *
     * @param username the username to check
     * @return true if the username is valid, false otherwise
     */
    private boolean checkUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return true;
    }
}