package interface_adaptors.login;

import use_case.login.LoginMediator;

/**
 * The {@code LoginController} class serves as an abstract base class for handling user login operations.
 * It utilizes a mediator pattern to interact with the business logic layer, delegating the login process
 * to the appropriate use case interactor (mediator).
 *
 * <p>This abstract class should be extended by specific implementations that provide concrete behavior
 * for different types of login mediators.</p>
 *
 * @param <LM> the type of {@code LoginMediator} that this controller interacts with
 *
 * <p><b>Author:</b> Jessica Chen</p>
 */
public abstract class LoginController<LM extends LoginMediator> {
    protected final LM loginMediator;

    /**
     * Constructs a {@code LoginController} object with the specified use case interactor (mediator).
     *
     * @param userAccountLoginMediator the use case interactor (mediator) responsible for handling user login operations
     */
    public LoginController(LM userAccountLoginMediator) {
        this.loginMediator = userAccountLoginMediator;
    }

    /**
     * Executes the login process using the provided identification and password.
     *
     * @param identification the identification provided by the user
     * @param password the password provided by the user
     */
    public void execute (String identification, String password) {}
}
