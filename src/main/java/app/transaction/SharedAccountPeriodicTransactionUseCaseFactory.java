package app.transaction;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionController;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionPresenter;
import interface_adaptors.transaction.periodic.shared_account.SharedAccountPeriodicTransactionViewModel;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionInteractor;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputBoundary;
import view.transaction.periodic.shared_account.SharedAccountPeriodicTransactionView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory class for creating components related to the shared account periodic transaction use case.
 * <p>
 * This factory is responsible for assembling the necessary components, such as the view,
 * controller, interactor, and presenter, for handling periodic transactions associated with shared accounts.
 * </p>
 * @author Eric
 * @author Zella
 */
public class SharedAccountPeriodicTransactionUseCaseFactory {
    private SharedAccountPeriodicTransactionUseCaseFactory() {}

    /**
     * Creates and returns the view for the shared account periodic transaction use case.
     * <p>
     * This method sets up the necessary components, including the controller and view model,
     * for handling periodic transactions and returns the assembled view.
     * </p>
     *
     * @param viewManagerModel the model responsible for managing view transitions
     * @param viewModel the view model that holds the state of the periodic transaction
     * @return the assembled SharedAccountPeriodicTransactionView
     */
    public static SharedAccountPeriodicTransactionView create(ViewManagerModel viewManagerModel,
                                                              SharedAccountPeriodicTransactionViewModel viewModel) {
        try {
            SharedAccountPeriodicTransactionController sharedAccountPeriodicTransactionController =
                    createSharedAccountPeriodicUseCase(viewManagerModel, viewModel);
            return new SharedAccountPeriodicTransactionView(viewModel, sharedAccountPeriodicTransactionController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open shared account data file.");
        }
        return null;
    }

    /**
     * Creates and returns the controller for handling shared account periodic transactions.
     * <p>
     * This method sets up the interactor and presenter required to manage shared account periodic transactions,
     * and returns a controller to facilitate interactions between the user and the use case.
     * </p>
     *
     * @param viewManager the model responsible for managing view transitions
     * @param viewModel the view model that holds the state of the periodic transaction
     * @return the assembled SharedAccountPeriodicTransactionController
     * @throws IOException if an error occurs while accessing the shared account data file
     */
    private static SharedAccountPeriodicTransactionController createSharedAccountPeriodicUseCase(
            ViewManagerModel viewManager,
            SharedAccountPeriodicTransactionViewModel viewModel) throws IOException {

        // Use SharedAccountDataAccessInterface for accessing shared account data
        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getShareAccountDataAccessObject();

        // Presenter for handling shared account periodic transactions
        SharedAccountPeriodicTransactionOutputBoundary presenter =
                new SharedAccountPeriodicTransactionPresenter(viewModel, viewManager);

        // Retrieve shared account by its ID
        SharedAccount sharedAccount = dataAccessObject.getById(viewManager.getUserId());

        // Interactor for shared account periodic transactions
        SharedAccountPeriodicTransactionInteractor interactor =
                new SharedAccountPeriodicTransactionInteractor(dataAccessObject, sharedAccount, presenter);

        // Return the controller with the interactor and view model
        return new SharedAccountPeriodicTransactionController(interactor, viewModel);
    }
}

