package interface_adaptors.signup.user_account;

import interface_adaptors.signup.SignupController;
import use_case.signup.user_account.UserAccountSignupInputBoundary;
import use_case.signup.user_account.UserAccountSignupInputData;
import use_case.signup.user_account.UserAccountSignupInteractor;

/**
 * The {@code UserAccountSignupController} class handles user interactions related to the
 * signup process for user accounts. It communicates with the use case interactor to
 * execute the signup process, ensuring that the user's details are correctly processed
 * according to the business logic.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture,
 * facilitating the flow of data between the user interface and the underlying
 * business logic for user account signup.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen
 * </p>
 */
public class UserAccountSignupController extends SignupController<
        UserAccountSignupInputBoundary,
        UserAccountSignupInteractor> {

    /**
     * Constructs a {@code UserAccountSignupController} object with the specified use case interactor.
     *
     * @param signupInteractor the use case interactor responsible for handling the standard user signup process.
     */
    public UserAccountSignupController(UserAccountSignupInteractor signupInteractor) {
        super(signupInteractor);
    }

    /**
     * Executes the standard signup process with the given user details.
     * This method creates a {@code UserAccountSignupInputData} object containing the
     * user's username, password, and identification, and passes it to the interactor
     * to perform the signup operation.
     *
     * @param username        the username of the user.
     * @param password        the password of the user.
     * @param identification  the identification of the user.
     */
    public void execute(String username, String password, String identification) {
        UserAccountSignupInputData userAccountSignupInputData = new UserAccountSignupInputData(username, password, identification);
        signupInteractor.execute(userAccountSignupInputData);
    }

}
