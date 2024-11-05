package app.authentication;

import data_access.*;
import data_access.account.user_account.UserAccountDataAccessInterface;
import data_access.authentication.user_account.UserAccountLoginDataAccessInterface;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.login.user_account.UserAccountLoginController;
import interface_adaptors.login.user_account.UserAccountLoginPresenter;
import interface_adaptors.login.user_account.UserAccountLoginViewModel;
import use_case.login.user_account.UserAccountLoginInteractor;
import use_case.login.user_account.UserAccountLoginMediator;
import use_case.login.user_account.UserAcountLoginOutputBoundary;
import use_case.update_periodic_at_login.user_account.UserAccountUpdatePeriodicAtLoginInteractor;
import view.login.user_account.UserAccountLoginView;

import javax.swing.*;
import java.io.IOException;

/**
 * The UserAccountLoginUseCaseFactory class is responsible for creating and
 * initializing
 * the components required for user account login functionality. This factory
 * sets up
 * the interactor, mediator, controller, presenter, and view needed for the
 * login use case.
 * <p>
 * It provides a static method to create a view, which internally sets up all
 * the necessary components and handles
 * potential exceptions related to file operations.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
public class UserAccountLoginUseCaseFactory {

    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private UserAccountLoginUseCaseFactory() {
    }

    /**
     * Creates and returns a {@link UserAccountLoginView} initialized with the
     * required components.
     *
     * @param viewManagerModel the view manager model used to manage the view state
     * @param loginViewModel   the view model for the user login functionality
     * @return a {@link UserAccountLoginView} instance if successful, or null if an
     *         IOException occurs
     */
    public static UserAccountLoginView create(ViewManagerModel viewManagerModel,
            UserAccountLoginViewModel loginViewModel) {
        try {
            UserAccountLoginController loginController = createUserLoginUseCase(viewManagerModel, loginViewModel);
            return new UserAccountLoginView(loginViewModel, loginController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and initializes the components required for user account login
     * functionality.
     * This includes creating the data access objects, interactor, presenter,
     * mediator, and controller.
     *
     * @param viewManagerModel the view manager used to manage the view state
     * @param loginViewModel   the view model for the user login functionality
     * @return a {@link UserAccountLoginController} instance
     * @throws IOException if an error occurs while accessing user data
     */
    private static UserAccountLoginController createUserLoginUseCase(ViewManagerModel viewManagerModel,
            UserAccountLoginViewModel loginViewModel) throws IOException {
        UserAccountLoginDataAccessInterface loginDataAccessObject = DAOFactory.getLoginDataAccessObject();
        UserAccountDataAccessInterface periodicTransactionDataAccessObject = DAOFactory.getPeriodicTransactionDAO();

        UserAcountLoginOutputBoundary loginPresenter = new UserAccountLoginPresenter(viewManagerModel, loginViewModel);
        UserAccountLoginInteractor loginInteractor = new UserAccountLoginInteractor(loginDataAccessObject,
                loginPresenter);

        UserAccountUpdatePeriodicAtLoginInteractor updatePeriodicAtLoginInteractor = new UserAccountUpdatePeriodicAtLoginInteractor(
                periodicTransactionDataAccessObject);

        UserAccountLoginMediator userAccountLoginMediator = new UserAccountLoginMediator(loginInteractor,
                updatePeriodicAtLoginInteractor, periodicTransactionDataAccessObject);
        loginInteractor.setMediator(userAccountLoginMediator);
        return new UserAccountLoginController(userAccountLoginMediator);
    }
}
