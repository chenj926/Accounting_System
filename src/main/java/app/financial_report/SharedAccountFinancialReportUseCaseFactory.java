package app.financial_report;

import data_access.DAOFactory;
import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportController;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportPresenter;
import interface_adaptors.financial_report.shared_account.SharedAccountFinancialReportViewModel;
import use_case.financial_report.financial_report.shared_account.SharedAccountFinancialReportInteractor;
import use_case.financial_report.financial_report.shared_account.SharedAccountFinancialReportOutputBoundary;
import view.financial_report.shared_account.shared_account.SharedAccountFinancialReportView;

import javax.swing.*;
import java.io.IOException;

/**
 * The SharedAccountFinancialReportUseCaseFactory class is responsible for creating and initializing
 * the components required for generating financial reports for shared accounts. This factory sets up
 * the interactor, presenter, controller, and view needed for this use case.
 * <p>
 * It provides a static method to create a view, which internally sets up all the necessary components and handles
 * potential exceptions related to file operations.
 * </p>
 *
 * @author Eric
 */
public class SharedAccountFinancialReportUseCaseFactory {

    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private SharedAccountFinancialReportUseCaseFactory() {}

    /**
     * Creates and returns a {@link SharedAccountFinancialReportView} initialized with the required components.
     *
     * @param viewManager the view manager model used to manage the view state
     * @param viewModel the view model for the financial report functionality
     * @return a {@link SharedAccountFinancialReportView} instance if successful, or null if an IOException occurs
     */
    public static SharedAccountFinancialReportView create(ViewManagerModel viewManager,
                                                          SharedAccountFinancialReportViewModel viewModel) {
        try {
            SharedAccountFinancialReportController controller = createSharedAccountFinancialReportUseCase(viewManager, viewModel);
            return new SharedAccountFinancialReportView(viewModel, controller, viewManager);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and initializes the components required for generating financial reports for shared accounts.
     * This includes creating the data access object, presenter, interactor, and controller.
     *
     * @param viewManagerModel the view manager used to manage the view state
     * @param viewModel the view model for the financial report functionality
     * @return a {@link SharedAccountFinancialReportController} instance
     * @throws IOException if an error occurs while accessing user data
     */
    private static SharedAccountFinancialReportController createSharedAccountFinancialReportUseCase(ViewManagerModel viewManagerModel,
                                                                          SharedAccountFinancialReportViewModel viewModel) throws IOException {
        SharedAccountDataAccessInterface dataAccessObject = DAOFactory.getSharedAccountFinancialReportDAO();
        SharedAccountFinancialReportOutputBoundary presenter = new SharedAccountFinancialReportPresenter(viewModel, viewManagerModel);
        SharedAccount account = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactors for standard and shared account signups
        SharedAccountFinancialReportInteractor interactor = new SharedAccountFinancialReportInteractor(account, presenter, dataAccessObject);

        // Return the controller that can handle both types of signups
        return new SharedAccountFinancialReportController(interactor, viewModel);
    }
}
