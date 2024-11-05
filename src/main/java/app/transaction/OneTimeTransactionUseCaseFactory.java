package app.transaction;

import data_access.DAOFactory;
import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import interface_adaptors.*;
import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.user_account.UserAccountOneTimeTransactionPresenter;
import interface_adaptors.transaction.one_time.user_account.UserOneTimeTransactionViewModel;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionInteractor;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputBoundary;
import view.transaction.one_time.user_account.UserAccountOneTimeTransactionView;

import javax.swing.*;
import java.io.IOException;

/**
 * The OneTimeTransactionUseCaseFactory class is responsible for creating and initializing the components required
 * for handling one-time transactions in user accounts. This factory sets up the interactor, presenter, controller,
 * and view for the one-time transaction use case.
 * <p>
 * It provides a static method to create a view, which internally sets up all the necessary components and handles
 * potential exceptions related to file operations.
 * </p>
 *
 * @author Eric
 */
public class OneTimeTransactionUseCaseFactory {
    private OneTimeTransactionUseCaseFactory() {}

    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    public static UserAccountOneTimeTransactionView create(ViewManagerModel viewManagerModel,
                                                           UserOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel) {
        try {
            UserAccountOneTimeTransactionController userAccountOneTimeTransactionController = createUserOneTimeUseCase(viewManagerModel,
                    userAccountOneTimeTransactionViewModel);
            return new UserAccountOneTimeTransactionView(userAccountOneTimeTransactionViewModel, userAccountOneTimeTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and returns a {@link UserAccountOneTimeTransactionView} initialized with the required components.
     *
     * @param viewManagerModel the view manager model used to manage the view state
     * @param userAccountOneTimeTransactionViewModel the view model for one-time transactions
     * @return a {@link UserAccountOneTimeTransactionView} instance if successful, or null if an IOException occurs
     */
    private static UserAccountOneTimeTransactionController createUserOneTimeUseCase(ViewManagerModel viewManagerModel,
                                                                                    UserOneTimeTransactionViewModel userAccountOneTimeTransactionViewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getOneTimeTransactionDAO();
        UserAccountOneTimeTransactionOutputBoundary presenter = new UserAccountOneTimeTransactionPresenter(userAccountOneTimeTransactionViewModel, viewManagerModel);

        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        UserAccountOneTimeTransactionInteractor interactor = new UserAccountOneTimeTransactionInteractor(dataAccessObject, presenter, userAccount);
        return new UserAccountOneTimeTransactionController(interactor, userAccountOneTimeTransactionViewModel);
    }

}
