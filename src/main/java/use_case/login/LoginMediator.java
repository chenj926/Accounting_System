package use_case.login;

import data_access.account.AccountDataAccessInterface;
import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputData;

//import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputBoundary;
//import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputData;

/**
 * The {@code LoginMediator} class mediates the interaction between the login
 * process and periodic transaction updates.
 * <p>
 * This class coordinates actions that need to occur after a successful login,
 * such as updating periodic transactions.
 * It acts as a mediator between the login process and the post-login updates.
 * </p>
 *
 * @param <LInputBoundary>      the type of the login input boundary
 * @param <UPLInputBoundary>    the type of the update periodic at login input
 *                              boundary
 * @param <DataAccessInterface> the type of the data access interface for
 *                              account data
 * @param <LInputData>          the type of the login input data
 * @param <UPLInputData>        the type of the update periodic at login input
 *                              data
 *
 * @author Jessica
 */
public class LoginMediator<LInputBoundary extends LoginInputBoundary<LInputData>, UPLInputBoundary extends AccountUpdatePeriodicAtLoginInputBoundary<UPLInputData>, DataAccessInterface extends AccountDataAccessInterface, LInputData extends LoginInputData, UPLInputData extends AccountUpdatePeriodicAtLoginInputData> {

    protected final LInputBoundary loginInteractor;
    protected final UPLInputBoundary updatePeriodicAtLoginInteractor;
    protected final DataAccessInterface periodicTransactionDataAccessObject;
    // private LoginPresenter<?> presenter;

    /**
     * Constructs a LoginMediator object with the specified interactors.
     *
     * @param loginInteractor                     the login interactor
     * @param periodicTransactionDataAccessObject the periodic transaction
     *                                            interactor
     */
    public LoginMediator(LInputBoundary loginInteractor,
            UPLInputBoundary updatePeriodicAtLoginInteractor,
            DataAccessInterface periodicTransactionDataAccessObject) {
        this.loginInteractor = loginInteractor;
        this.updatePeriodicAtLoginInteractor = updatePeriodicAtLoginInteractor;
        this.periodicTransactionDataAccessObject = periodicTransactionDataAccessObject;
    }

    /**
     * Executes the login process using the provided login input data.
     * <p>
     * This method triggers the login process by invoking the login interactor's
     * execute method.
     * </p>
     *
     * @param loginInputData the input data required for the login process
     */
    public void execute(LInputData loginInputData) {
        this.loginInteractor.execute(loginInputData);
    }

    /**
     * Notifies the mediator of the result of the login process and triggers
     * post-login actions if successful.
     * <p>
     * If the login is successful, this method will trigger the execution of the
     * periodic transaction updates.
     * </p>
     *
     * @param success                        {@code true} if the login was
     *                                       successful; {@code false} otherwise
     * @param updatePeriodicAtLoginInputData the input data required for updating
     *                                       periodic transactions
     */
    public void notifyLoginResult(boolean success, UPLInputData updatePeriodicAtLoginInputData) {
        if (success) {
            updatePeriodicAtLoginInteractor.execute(updatePeriodicAtLoginInputData);
        }
    }
}
