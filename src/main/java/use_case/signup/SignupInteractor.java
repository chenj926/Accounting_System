package use_case.signup;

import data_access.authentication.AccountSignupDataAccessInterface;
import entity.account.AccountFactory;

/**
 * Abstract base class for account sign-up interactors.
 * Provides common functionality for sign-up processes, including validation
 * methods for the identification and password fields.
 *
 * @param <T> the type of data access interface for account signup
 * @param <I> the type of input data that extends {@link SignupInputData}
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
public abstract class SignupInteractor<
        T extends AccountSignupDataAccessInterface,
        I extends SignupInputData> {
    protected final AccountFactory accountFactory;
    protected final T userDataAccessObject;

    /**
     * Constructs a SignupInteractor object with the specified data access interface
     * and account factory.
     *
     * @param userSignupDataAccessInterface the data access interface for account signup
     * @param accountFactory                 the factory for creating account entities
     */
    public SignupInteractor(T userSignupDataAccessInterface,
                                   AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
        this.userDataAccessObject = userSignupDataAccessInterface;
    }

    public abstract void execute(I signupInputData) throws Exception;
    /**
     * Validates if the provided password is valid (not null or empty).
     *
     * @param password the password to check
     * @return true if the password is valid, false otherwise
     */
    protected boolean checkPassword(String password) {
        return password != null && !password.isEmpty();
    }

    /**
     * Validates if the provided identification is valid (not null or empty).
     *
     * @param id the identification to check
     * @return true if the identification is valid, false otherwise
     */
    protected boolean checkIdentification(String id) {
        return id != null && !id.isEmpty();
    }
}