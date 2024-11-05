package interface_adaptors.financial_report.user_account;

import interface_adaptors.financial_report.FinancialReportController;
import use_case.financial_report.financial_report.user_account.UserAccountFinancialReportInputBoundary;
import use_case.financial_report.financial_report.user_account.UserAccountFinancialReportInputData;

/**
 * The {@code UserAccountFinancialReportController} class is responsible for handling user requests
 * to generate financial reports for a specific user account. It extends the generic
 * {@link FinancialReportController} class and uses a user account-specific interactor to perform
 * the report generation.
 *
 * <p>This controller is a crucial component in the interface adapters layer of Clean Architecture,
 * serving as the mediator between the UI and the use case interactor for financial report generation.
 * It processes input data from the user, initiates the generation of the report, and ensures that
 * the results are passed back to the view model for presentation.</p>
 *
 * <p><b>Authors:</b> Dana Huang, Eric Chen</p>
 */
public class UserAccountFinancialReportController extends FinancialReportController<
        UserAccountFinancialReportInputBoundary,
        UserAccountFinancialReportViewModel,
        UserAccountFinancialReportInputData,
        UserAccountFinancialReportState> {

    /**
     * Constructs a {@code UserAccountFinancialReportController} with the specified interactor
     * and view model.
     *
     * @param userAccountFinancialReportInteractor the use case interactor responsible for generating financial reports
     * @param viewModel                            the view model that will be updated with the results of the financial report
     */
    public UserAccountFinancialReportController(UserAccountFinancialReportInputBoundary
                                                        userAccountFinancialReportInteractor,
                                                UserAccountFinancialReportViewModel viewModel) {
        super(userAccountFinancialReportInteractor, viewModel);
    }

    /**
     * Executes the financial report generation process for the specified account.
     * This method creates a {@link UserAccountFinancialReportInputData} object with the provided
     * identification and passes it to the interactor to generate the report.
     *
     * @param identification the unique identifier for the user account for which the financial report is generated
     */
    public void execute(String identification) {
        UserAccountFinancialReportInputData userAccountFinancialReportInputData = new UserAccountFinancialReportInputData(identification);
        financialReportInteractor.execute(userAccountFinancialReportInputData);
    }
}




