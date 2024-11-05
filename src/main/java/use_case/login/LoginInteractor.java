package use_case.login;

/**
 * Abstract interactor class for handling the logic of the login use case.
 * <p>
 * This class defines the core structure for interacting with account data, processing login requests, and preparing the output view.
 * It follows the input/output boundary pattern and is intended to be extended by specific implementations for different types of accounts or login mechanisms.
 * </p>
 *
 * @param <T> the type of the data access object used to retrieve account data
 * @param <O> the type of the output boundary for preparing the view
 * @param <LO> the type of the output data for the login use case
 * @param <I> the type of the input data required for the login use case
 * @param <LM> the type of the mediator used to facilitate communication between components
 *
 * @see LoginInputBoundary
 * @see LoginOutputBoundary
 * @see LoginInputData
 * @see LoginOutputData
 * @see LoginMediator
 *
 * @author Eric
 * @author Jessica
 * @author Dana
 */
public abstract class LoginInteractor<
        T,
        O extends LoginOutputBoundary<LO>,
        LO extends LoginOutputData,
        I extends LoginInputData,
        LM extends LoginMediator>{
    protected T accountDataAccessObject;
    protected O presenter;
    protected LM mediator;

    public LoginInteractor(T accountDataAccessObject, O presenter) {
        this.accountDataAccessObject = accountDataAccessObject;
        this.presenter = presenter;
    }

    public abstract void execute(I inputData);

    /**
     * Sets the mediator for the interactor.
     *
     * @param mediator the UserAccountLoginMediator instance to set
     */
    public void setMediator(LM mediator) {
        this.mediator = mediator;
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
