package app.FinancialReport;

import data_access.DAOFactory;
import data_access.account.UserAccountDataAccessInterface;
import entity.account.UserAccount;
import interface_adaptors.financial_report.FinancialReportController;
import interface_adaptors.financial_report.UserAccountFinancialReportPresenter;
import interface_adaptors.financial_report.FinancialReportViewModel;
import interface_adaptors.ViewManagerModel;
import use_case.financial_report.UserAccountFinancialReportInteractor;
import use_case.financial_report.UserAccountFinancialReportOutputBoundary;
import view.financial_report.FinancialReportView;

import javax.swing.*;
import java.io.IOException;

public class FinancialReportUseCaseFactory {

    private FinancialReportUseCaseFactory() {}

    public static FinancialReportView create(ViewManagerModel viewManagerModel,
                                             FinancialReportViewModel viewModel) {
        try {
            FinancialReportController financialReportController = createFinancialReportUseCase(viewManagerModel, viewModel);
            return new FinancialReportView(viewModel, financialReportController, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static FinancialReportController createFinancialReportUseCase(ViewManagerModel viewManagerModel,
                                                                          FinancialReportViewModel viewModel) throws IOException {
        UserAccountDataAccessInterface dataAccessObject = DAOFactory.getFinancialReportDAO();
        UserAccountFinancialReportOutputBoundary presenter = new UserAccountFinancialReportPresenter(viewModel, viewManagerModel);
        UserAccount userAccount = dataAccessObject.getById(viewManagerModel.getUserId());

        // Create the interactors for standard and shared account signups
        UserAccountFinancialReportInteractor interactor = new UserAccountFinancialReportInteractor(userAccount, presenter, dataAccessObject);

        // Return the controller that can handle both types of signups
        return new FinancialReportController(interactor, viewModel);
    }
}
