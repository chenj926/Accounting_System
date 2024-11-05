package use_case.login.shared_account;

import data_access.authentication.shared_account.SharedAccountLoginDataAccessInterface;
import entity.account.shared_account.SharedAccount;
;
import use_case.login.LoginInteractor;
import use_case.update_periodic_at_login.shared_account.SharedAccountUpdatePeriodicAtLoginInputData;

import java.time.LocalDate;

/**
 * The {@code SharedAccountLoginInteractor} class extends {@link LoginInteractor} to handle login operations for shared accounts.
 * It includes additional validation for the shared account ID and ensures that the provided password matches.
 *
 * @author Xile
 * @author Eric
 * @author Jessica
 */
public class SharedAccountLoginInteractor extends LoginInteractor<
        SharedAccountLoginDataAccessInterface,
        SharedAccountLoginOutputBoundary,
        SharedAccountLoginOutputData,
        SharedAccountLoginInputData,
        SharedAccountLoginMediator> implements SharedAccountLoginInputBoundary{

    /**
     * Constructs a SharedAccountLoginInteractor object with the specified data access interfaces and output boundary.
     *
     * @param sharedAccountLoginDAO      the data access interface for user data
     * @param presenter           the output boundary for presenting the login results
     */
    public SharedAccountLoginInteractor(SharedAccountLoginDataAccessInterface sharedAccountLoginDAO,
                                        SharedAccountLoginOutputBoundary presenter) {
        super(sharedAccountLoginDAO, presenter);
    }

    /**
     * Executes the shared account login process with the given input data.
     *
     * @param sharedLoginInputData the input data required for the shared account login process
     */
    @Override
    public void execute(SharedAccountLoginInputData sharedLoginInputData) {

        boolean validSharedAccountId = this.checkIdentification(sharedLoginInputData.getIdentification());
        boolean validSharedAccountPassword = this.checkPassword(sharedLoginInputData.getPassword());

        if (!validSharedAccountPassword && !validSharedAccountId) {
            this.presenter.prepareFailView("Identification AND Password can not be empty!");
            return;
        } else if (!validSharedAccountPassword) {
            this.presenter.prepareFailView("Password can not be empty!");
            return;
        } else if (!validSharedAccountId) {
            this.presenter.prepareFailView("Identification can not be empty!");
            return;
        } else {
            if (!this.accountDataAccessObject.existById(sharedLoginInputData.getIdentification())) {
                this.presenter.prepareFailView("Shared Account not found");
                return;
            }

            SharedAccount sharedAccount = this.accountDataAccessObject.getById(sharedLoginInputData.getIdentification());
            if (sharedAccount != null && !sharedAccount.getPassword().equals(sharedLoginInputData.getPassword())) {
                this.presenter.prepareFailView("Incorrect Password!");
                return;
            }

            boolean isLogin = this.accountDataAccessObject.login(sharedAccount);

            if (!isLogin) {
                this.presenter.prepareFailView("Incorrect Account Password!");
            } else {
                SharedAccountLoginOutputData sharedLoginOutputData = new SharedAccountLoginOutputData(sharedLoginInputData.getIdentification(), true);
                this.presenter.prepareSuccessView(sharedLoginOutputData);

                // Notify mediator on successful login
                SharedAccountUpdatePeriodicAtLoginInputData sharedAccountUpdatePeriodicAtLoginInputData = new SharedAccountUpdatePeriodicAtLoginInputData(sharedLoginInputData.getIdentification(), LocalDate.now());
                mediator.notifyLoginResult(true, sharedAccountUpdatePeriodicAtLoginInputData);
            }
        }
    }

}
