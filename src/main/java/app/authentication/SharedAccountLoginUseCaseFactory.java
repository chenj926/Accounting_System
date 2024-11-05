package app.authentication;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import data_access.authentication.shared_account.SharedAccountLoginDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.shared_account.SharedAccountLoginController;
import interface_adaptors.login.shared_account.SharedAccountLoginPresenter;
import interface_adaptors.login.shared_account.SharedAccountLoginViewModel;
import use_case.login.shared_account.SharedAccountLoginInteractor;
import use_case.login.shared_account.SharedAccountLoginMediator;
import use_case.login.shared_account.SharedAccountLoginOutputBoundary;
import use_case.update_periodic_at_login.shared_account.SharedAccountUpdatePeriodicAtLoginInteractor;
import view.login.shared_account.SharedAccountLoginView;

import javax.swing.*;
import java.io.IOException;

/**
 * The SharedAccountLoginUseCaseFactory class is responsible for creating and
 * initializing
 * the components required for shared account login functionality. This factory
 * sets up
 * the interactors, controllers, presenters, and views necessary for shared
 * account login.
 * <p>
 * It provides a static method to create a view for shared account login and
 * handles
 * any potential exceptions related to file operations.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
public class SharedAccountLoginUseCaseFactory {

    /**
     * Creates and returns a {@link SharedAccountLoginView} initialized with the
     * required components
     * for shared account login functionality.
     *
     * @param viewManagerModel the view manager model used to manage the view state
     * @param viewModel        the view model for shared account login functionality
     * @return a {@link SharedAccountLoginView} instance if successful, or null if
     *         an IOException occurs
     */
    public static SharedAccountLoginView create(ViewManagerModel viewManagerModel,
            SharedAccountLoginViewModel viewModel) {
        try {
            SharedAccountLoginController controller = createSharedAccountUserLoginUseCase(viewManagerModel, viewModel);
            return new SharedAccountLoginView(viewModel, controller, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and initializes the components required for shared account login
     * functionality.
     * This includes creating the data access objects, interactor, presenter, and
     * controller.
     *
     * @param viewManagerModel the view manager used to manage the view state
     * @param viewModel        the view model for shared account login functionality
     * @return a {@link SharedAccountLoginController} instance
     * @throws IOException if an error occurs while accessing user data
     */
    private static SharedAccountLoginController createSharedAccountUserLoginUseCase(ViewManagerModel viewManagerModel,
            SharedAccountLoginViewModel viewModel) throws IOException {
        SharedAccountLoginDataAccessInterface loginDataAccessObject = DAOFactory
                .getSharedAccountLoginDataAccessObject();
        SharedAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory
                .getShareAccountDataAccessObject();

        SharedAccountLoginOutputBoundary presenter = new SharedAccountLoginPresenter(viewManagerModel, viewModel);
        SharedAccountLoginInteractor interactor = new SharedAccountLoginInteractor(loginDataAccessObject, presenter);

        SharedAccountUpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new SharedAccountUpdatePeriodicAtLoginInteractor(
                periodicTransactionDataAccessObject);

        SharedAccountLoginMediator sharedAccountLoginMediator = new SharedAccountLoginMediator(interactor,
                updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        interactor.setMediator(sharedAccountLoginMediator);
        return new SharedAccountLoginController(sharedAccountLoginMediator);
    }
}
