package interface_adaptors.signup.shared_account;

import interface_adaptors.signup.SignupController;
import use_case.signup.shared_account.SharedAccountSignupInputBoundary;
import use_case.signup.shared_account.SharedAccountSignupInputData;
import use_case.signup.shared_account.SharedAccountSignupInteractor;

import java.util.Set;

/**
 * The {@code SharedAccountSignupController} class handles user input for the shared account
 * signup process. It constructs an input data object with the provided user information and
 * delegates the execution to the associated signup interactor.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, ensuring that user
 * input is correctly processed and passed to the business logic layer for further handling.
 * </p>
 *
 * <p>
 * <b>Author:</b> Xile Chen, Eric Chen, Jessica Chen
 * </p>
 */
public class SharedAccountSignupController extends SignupController<
        SharedAccountSignupInputBoundary,
        SharedAccountSignupInteractor> {

    /**
     * Constructs a {@code SharedAccountSignupController} with the specified signup interactor.
     *
     * @param sharedAccountSignupInteractor the interactor responsible for processing the shared account signup data.
     */
    public SharedAccountSignupController(SharedAccountSignupInteractor sharedAccountSignupInteractor) {
        super(sharedAccountSignupInteractor);
    }

    /**
     * Executes the signup process with the given input data.
     * This method creates a {@code SharedAccountSignupInputData} object containing the shared account
     * ID, password, and a set of additional user IDs, and then delegates the signup process to the interactor.
     *
     * @param sharedAccountId       the shared account ID.
     * @param sharedAccountPassword the shared account password.
     * @param userIds               a set of additional user IDs.
     */
    public void execute(String sharedAccountId, String sharedAccountPassword, Set<String> userIds) {
        // Create input data object for the shared account signup
        System.out.println("in controller"+sharedAccountId+"\n"+sharedAccountPassword+"\n"+userIds.toString());
        SharedAccountSignupInputData sharedAccountSignupInputData = new SharedAccountSignupInputData(
                sharedAccountId,
                sharedAccountPassword,
                userIds
        );

        // Execute the signup process using the interactor
        signupInteractor.execute(sharedAccountSignupInputData);
    }
}

