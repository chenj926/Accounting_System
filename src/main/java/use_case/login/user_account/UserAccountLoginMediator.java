package use_case.login.user_account;

import data_access.account.user_account.UserAccountDataAccessInterface;
import org.springframework.stereotype.Service;
import use_case.login.LoginMediator;
import use_case.update_periodic_at_login.user_account.UserAccountUpdatePeriodicAtLoginInputBoundary;
import use_case.update_periodic_at_login.user_account.UserAccountUpdatePeriodicAtLoginInputData;

/**
 * The {@code UserAccountLoginMediator} class mediates the interaction between
 * the login process and periodic transaction updates for user accounts.
 * <p>
 * This class coordinates actions that need to occur after a successful login,
 * such as updating periodic transactions, specifically for user accounts.
 * It extends the {@link LoginMediator} to provide specific functionality for
 * user account logins.
 * </p>
 *
 * @see LoginMediator
 * @see UserAccountLoginInputBoundary
 * @see UserAccountUpdatePeriodicAtLoginInputBoundary
 * @see UserAccountDataAccessInterface
 * @see UserAccountLoginInputData
 * @see UserAccountUpdatePeriodicAtLoginInputData
 *
 * @author Jessica
 */
@Service
public class UserAccountLoginMediator extends
        LoginMediator<UserAccountLoginInputBoundary, UserAccountUpdatePeriodicAtLoginInputBoundary, UserAccountDataAccessInterface, UserAccountLoginInputData, UserAccountUpdatePeriodicAtLoginInputData> {

    /**
     * Constructs a {@code UserAccountLoginMediator} with the specified interactors
     * and data access object.
     *
     * @param loginInputData                  the input boundary responsible for
     *                                        handling the login process for user
     *                                        accounts
     * @param updatePeriodicAtLoginInteractor the interactor responsible for
     *                                        updating periodic transactions
     *                                        post-login
     * @param accountDataAccessObject         the data access object for handling
     *                                        user account data
     */
    public UserAccountLoginMediator(UserAccountLoginInputBoundary loginInputData,
            UserAccountUpdatePeriodicAtLoginInputBoundary updatePeriodicAtLoginInteractor,
            UserAccountDataAccessInterface accountDataAccessObject) {
        super(loginInputData, updatePeriodicAtLoginInteractor, accountDataAccessObject);
    }
}
