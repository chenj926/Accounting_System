package app.authentication;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import data_access.authentication.shared_account.SharedAccountSignupDataAccessInterface;
import data_access.authentication.user_account.UserSignupDataAccessInterface;
import entity.account.AccountFactory;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.signup.shared_account.SharedAccountSignupController;
import interface_adaptors.signup.shared_account.SharedAccountSignupPresenter;
import interface_adaptors.signup.shared_account.SharedAccountSignupViewModel;
import interface_adaptors.signup.user_account.UserAccountSignupController;
import interface_adaptors.signup.user_account.UserAccountSignupPresenter;
import interface_adaptors.signup.user_account.UserAccountSignupViewModel;
import use_case.signup.shared_account.SharedAccountSignupInteractor;
import use_case.signup.shared_account.SharedAccountSignupOutputBoundary;
import use_case.signup.user_account.UserAccountSignupInteractor;
import use_case.signup.user_account.UserAccountSignupOutputBoundary;
import view.signup.user_account.UserAccountSignupView;
import view.signup.shared_account.SharedAccountSignupView;

import javax.swing.*;
import java.io.IOException;

/**
 * The SignupUseCaseFactory class is responsible for creating and initializing the components
 * required for user account and shared account signup functionalities. This factory sets up
 * the interactors, controllers, presenters, and views needed for both types of signups.
 * <p>
 * It provides static methods to create views for user accounts and shared accounts, handling
 * any potential exceptions related to file operations.
 * </p>
 *
 * @author Jessica
 * @author Eric
 * @author Xile
 */
public class SignupUseCaseFactory {

    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private SignupUseCaseFactory() {}

    /**
     * Creates and returns a {@link UserAccountSignupView} initialized with the required components
     * for user account signup functionality.
     *
     * @param viewManagerModel the view manager model used to manage the view state
     * @param userAccountSignupViewModel the view model for user account signup functionality
     * @return a {@link UserAccountSignupView} instance if successful, or null if an IOException occurs
     */
    public static UserAccountSignupView create(ViewManagerModel viewManagerModel, UserAccountSignupViewModel userAccountSignupViewModel) {
        try {
            UserAccountSignupController userAccountSignupController = createUserSignupUseCase(viewManagerModel, userAccountSignupViewModel);
            return new UserAccountSignupView(userAccountSignupViewModel, userAccountSignupController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and returns a {@link SharedAccountSignupView} initialized with the required components
     * for shared account signup functionality.
     *
     * @param viewManagerModel the view manager model used to manage the view state
     * @param sharedAccountSignupViewModel the view model for shared account signup functionality
     * @return a {@link SharedAccountSignupView} instance if successful, or null if an IOException occurs
     */
    public static SharedAccountSignupView createSharedAccount(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedAccountSignupViewModel) {
        try {
            SharedAccountSignupController sharedAccountSignupController = createSharedAccountSignupUseCase(viewManagerModel, sharedAccountSignupViewModel);
            return new SharedAccountSignupView(sharedAccountSignupViewModel, sharedAccountSignupController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and initializes the components required for user account signup functionality.
     * This includes creating the data access objects, interactor, presenter, and controller.
     *
     * @param viewManagerModel the view manager used to manage the view state
     * @param userAccountSignupViewModel the view model for user account signup functionality
     * @return a {@link UserAccountSignupController} instance
     * @throws IOException if an error occurs while accessing user data
     */
    private static UserAccountSignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, UserAccountSignupViewModel userAccountSignupViewModel) throws IOException {
        UserSignupDataAccessInterface dataAccessObject = DAOFactory.getUserSignupDataAccessObject();
        UserAccountSignupOutputBoundary presenter = new UserAccountSignupPresenter(viewManagerModel, userAccountSignupViewModel);
        AccountFactory accountFactory = new AccountFactory();

        // Create the general signup interactor
        UserAccountSignupInteractor signupInteractor = new UserAccountSignupInteractor(dataAccessObject, presenter, accountFactory);

        // Return the controller for standard signup
        return new UserAccountSignupController(signupInteractor); // Pass null for Interactor
    }

    /**
     * Creates and initializes the components required for shared account signup functionality.
     * This includes creating the data access objects, interactor, presenter, and controller.
     *
     * @param viewManagerModel the view manager used to manage the view state
     * @param sharedAccountSignupViewModel the view model for shared account signup functionality
     * @return a {@link SharedAccountSignupController} instance
     * @throws IOException if an error occurs while accessing user data
     */
    private static SharedAccountSignupController createSharedAccountSignupUseCase(ViewManagerModel viewManagerModel, SharedAccountSignupViewModel sharedAccountSignupViewModel) throws IOException {
        SharedAccountSignupDataAccessInterface dataAccessObject = DAOFactory.getSharedAccountSignupDataAccessObject();
        SharedAccountDataAccessInterface sharedDataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SharedAccountSignupOutputBoundary presenter = new SharedAccountSignupPresenter(viewManagerModel, sharedAccountSignupViewModel);
        AccountFactory accountFactory = new AccountFactory();

        SharedAccountSignupInteractor signupInteractor = new SharedAccountSignupInteractor(dataAccessObject, sharedDataAccessObject, presenter, accountFactory);
        return new SharedAccountSignupController(signupInteractor); // Pass null for sharedInteractor
    }
}



