package app.transaction;

import data_access.DAOFactory;
import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import interface_adaptors.*;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionPresenter;
import interface_adaptors.transaction.periodic.user_account.UserAccountPeriodicTransactionViewModel;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionInteractor;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputBoundary;
import view.transaction.periodic.user_account.UserAccountPeriodicTransactionView;

import javax.swing.*;
import java.io.IOException;

/**
 * The PeriodicTransactionUseCaseFactory class is responsible for creating and initializing the components required
 * for handling periodic transactions in user accounts. This factory sets up the interactor, presenter, controller,
 * and view for the periodic transaction use case.
 * <p>
 * It provides a static method to create a view, which internally sets up all the necessary components and handles
 * potential exceptions related to file operations.
 * </p>
 *
 * @author Jessica
 * @author Eric
 */
public class PeriodicTransactionUseCaseFactory {
    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private PeriodicTransactionUseCaseFactory() {}

    /**
     * Creates and returns a {@link UserAccountPeriodicTransactionView} initialized with the required components.
     *
     * @param viewManagerModel the view manager model used to manage the view state
     * @param viewModel the view model for periodic transactions
     * @return a {@link UserAccountPeriodicTransactionView} instance if successful, or null if an IOException occurs
     */
    public static UserAccountPeriodicTransactionView create(ViewManagerModel viewManagerModel,
                                                            UserAccountPeriodicTransactionViewModel viewModel) {
        try {
            UserAccountPeriodicTransactionController userAccountPeriodicTransactionController = createUserPeriodicUseCase(viewManagerModel,
                    viewModel);
            return new UserAccountPeriodicTransactionView(viewModel, userAccountPeriodicTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and initializes the components required for handling periodic transactions in user accounts.
     * This includes creating the data access object, presenter, interactor, and controller.
     *
     * @param viewManager the view manager used to manage the view state
     * @param viewModel the view model for periodic transactions
     * @return a {@link UserAccountPeriodicTransactionController} instance
     * @throws IOException if an error occurs while accessing user data
     */
    private static UserAccountPeriodicTransactionController createUserPeriodicUseCase(ViewManagerModel viewManager,
                                                                                      UserAccountPeriodicTransactionViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getPeriodicTransactionDAO();
        UserAccountPeriodicTransactionOutputBoundary presenter = new UserAccountPeriodicTransactionPresenter(viewModel, viewManager);
        UserAccount userAccount = dataAccessObject.getById(viewManager.getUserId());
        UserAccountPeriodicTransactionInteractor interactor = new UserAccountPeriodicTransactionInteractor(dataAccessObject, presenter, userAccount);
        return new UserAccountPeriodicTransactionController(interactor, viewModel);
    }

}
