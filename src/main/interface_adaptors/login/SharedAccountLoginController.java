package interface_adaptors.login;

import use_case.login.*;

public class SharedAccountLoginController extends AccountLoginController {
    /**
     * Constructs a LoginController object with the specified use case interactor(mediator).
     *
     * @param userAccountLoginMediator the use case interactor(mediator) for user login
     */
    public SharedAccountLoginController(UserAccountLoginMediator userAccountLoginMediator) {
        super(userAccountLoginMediator);
    }

    /**
     * Constructs a LoginInputData object with the specified password and identification.
     *
     * @param sharedIdentification   the identification for the login
     * @param sharedPassword         the password for the login
     */
    @Override
    public void execute(String sharedIdentification, String sharedPassword) {
        SharedAccountLoginInputData sharedAccountLoginInputData = new SharedAccountLoginInputData(sharedIdentification, sharedPassword);
        this.userAccountLoginMediator.execute(sharedAccountLoginInputData);
    }
}
