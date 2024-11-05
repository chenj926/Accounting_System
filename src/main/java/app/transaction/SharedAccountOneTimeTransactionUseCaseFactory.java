package app.transaction;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionController;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionPresenter;
import interface_adaptors.transaction.one_time.shared_account.SharedAccountOneTimeTransactionViewModel;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionInteractor;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputBoundary;
import view.transaction.one_time.shared_account.SharedAccountOneTimeTransactionView;

import javax.swing.*;
import java.io.IOException;

/**
 * The SharedAccountOneTimeTransactionUseCaseFactory class is responsible for creating and initializing the components
 * required for handling one-time transactions in shared accounts. This factory class sets up the interactor, presenter,
 * controller, and view for the one-time transaction use case.
 * <p>
 * It provides a static method to create a view, which internally sets up all the necessary components using a factory
 * method and handles potential exceptions related to file operations.
 * </p>
 *
 * @author Xile
 * @author Eric
 */
public class SharedAccountOneTimeTransactionUseCaseFactory {

    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private SharedAccountOneTimeTransactionUseCaseFactory() {}

    /**
     * Creates and returns a {@link SharedAccountOneTimeTransactionView} initialized with the required components.
     *
     * @param viewManagerModel the view manager model used to manage the view state
     * @param oneTimeTransactionViewModel the view model for one-time transactions
     * @return a {@link SharedAccountOneTimeTransactionView} instance if successful, or null if an IOException occurs
     */
    public static SharedAccountOneTimeTransactionView create(ViewManagerModel viewManagerModel,
                                                SharedAccountOneTimeTransactionViewModel oneTimeTransactionViewModel) {
        try {
            SharedAccountOneTimeTransactionController oneTimeTransactionController = createSharedAccountOneTimeUseCase(viewManagerModel,
                    oneTimeTransactionViewModel);
            return new SharedAccountOneTimeTransactionView(oneTimeTransactionViewModel, oneTimeTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and initializes the components required for handling one-time transactions in shared accounts.
     * This includes creating the data access object, presenter, interactor, and controller.
     *
     * @param viewManagerModel the view manager model used to manage the view state
     * @param oneTimeTransactionViewModel the view model for one-time transactions
     * @return a {@link SharedAccountOneTimeTransactionController} instance
     * @throws IOException if an error occurs while accessing user data
     */
    private static SharedAccountOneTimeTransactionController createSharedAccountOneTimeUseCase(ViewManagerModel viewManagerModel,
                                                                                               SharedAccountOneTimeTransactionViewModel oneTimeTransactionViewModel) throws IOException {
        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getShareAccountDataAccessObject();
        SharedAccountOneTimeTransactionOutputBoundary presenter = new SharedAccountOneTimeTransactionPresenter(oneTimeTransactionViewModel, viewManagerModel);
        SharedAccount sharedAccount = dataAccessObject.getById(viewManagerModel.getUserId());
        SharedAccountOneTimeTransactionInteractor interactor = new SharedAccountOneTimeTransactionInteractor(dataAccessObject, presenter, sharedAccount);
        return new SharedAccountOneTimeTransactionController(interactor, oneTimeTransactionViewModel);
    }

}



