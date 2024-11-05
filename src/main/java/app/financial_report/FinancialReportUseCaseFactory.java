package app.financial_report;

import data_access.DAOFactory;
import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportController;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportPresenter;
import interface_adaptors.financial_report.user_account.UserAccountFinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import use_case.financial_report.financial_report.user_account.UserAccountFinancialReportInteractor;
import use_case.financial_report.financial_report.user_account.UserAccountFinancialReportOutputBoundary;
import view.financial_report.user_account.user_account.UserAccountFinancialReportView;

import javax.swing.*;
import java.io.IOException;

/**
 * The FinancialReportUseCaseFactory class is responsible for creating and initializing
 * the components required for generating financial reports for user accounts. This factory sets up
 * the interactor, presenter, controller, and view needed for this use case.
 * <p>
 * It provides a static method to create a view, which internally sets up all the necessary components and handles
 * potential exceptions related to file operations.
 * </p>
 *
 * @author Eric
 * @author Dana
 */
public class FinancialReportUseCaseFactory {

    /**
     * Private constructor to prevent instantiation of this factory class.
     */
    private FinancialReportUseCaseFactory() {}

    /**
     * Creates and returns a {@link UserAccountFinancialReportView} initialized with the required components.
     *
     * @param viewManagerModel the view manager model used to manage the view state
     * @param viewModel the view model for the financial report functionality
     * @return a {@link UserAccountFinancialReportView} instance if successful, or null if an IOException occurs
     */
    public static UserAccountFinancialReportView create(ViewManagerModel viewManagerModel,
                                                        UserAccountFinancialReportViewModel viewModel) {
        try {
            UserAccountFinancialReportController userAccountFinancialReportController = createFinancialReportUseCase(viewManagerModel, viewModel);
            return new UserAccountFinancialReportView(viewModel, userAccountFinancialReportController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    /**
     * Creates and initializes the components required for generating financial reports for user accounts.
     * This includes creating the data access object, presenter, interactor, and controller.
     *
     * @param viewManagerModel the view manager used to manage the view state
     * @param viewModel the view model for the financial report functionality
     * @return a {@link UserAccountFinancialReportController} instance
     * @throws IOException if an error occurs while accessing user data
     */
    private static UserAccountFinancialReportController createFinancialReportUseCase(ViewManagerModel viewManagerModel,
                                                                                     UserAccountFinancialReportViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getFinancialReportDAO();
        UserAccountFinancialReportOutputBoundary presenter = new UserAccountFinancialReportPresenter(viewModel, viewManagerModel);
        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactors for standard and shared account signups
        UserAccountFinancialReportInteractor interactor = new UserAccountFinancialReportInteractor(userAccount, presenter, dataAccessObject);

        // Return the controller that can handle both types of signups
        return new UserAccountFinancialReportController(interactor, viewModel);
    }
}
