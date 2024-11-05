package interface_adaptors.login.shared_account;

import interface_adaptors.login.LoginController;
import use_case.login.shared_account.SharedAccountLoginInputData;
import use_case.login.shared_account.SharedAccountLoginMediator;

/**
 * The {@code SharedAccountLoginController} class is responsible for handling the login process
 * for shared accounts. It extends the {@code LoginController} and utilizes the
 * {@code SharedAccountLoginMediator} to manage the interaction between the input data and the
 * login use case.
 * <p>
 * This controller is part of the presentation layer in the Clean Architecture, isolating the
 * application's core business logic from external elements like UI components.
 * The controller processes user input and directs it to the appropriate use case interactor
 * for shared account login.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen, Dana Huang
 * </p>
 */
public class SharedAccountLoginController extends LoginController<SharedAccountLoginMediator> {
    /**
     * Constructs a {@code SharedAccountLoginController} object with the specified use case interactor (mediator).
     *
     * @param loginMediator the use case interactor (mediator) responsible for handling the shared account login logic.
     */
    public SharedAccountLoginController(SharedAccountLoginMediator loginMediator) {
        super(loginMediator);
    }

    /**
     * Executes the login process by constructing a {@code SharedAccountLoginInputData} object
     * with the provided identification and password, and passing it to the associated mediator
     * for further processing.
     *
     * @param sharedIdentification the identification for the shared account login.
     * @param sharedPassword       the password for the shared account login.
     */
    @Override
    public void execute(String sharedIdentification, String sharedPassword) {
        SharedAccountLoginInputData sharedAccountLoginInputData = new SharedAccountLoginInputData(sharedIdentification, sharedPassword);
        this.loginMediator.execute(sharedAccountLoginInputData);
    }
}
