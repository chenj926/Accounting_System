package interface_adaptors.financial_report.shared_account;

import interface_adaptors.financial_report.FinancialReportController;
import use_case.financial_report.financial_report.shared_account.SharedAccountFinancialReportInputBoundary;
import use_case.financial_report.financial_report.shared_account.SharedAccountFinancialReportInputData;

/**
 * The {@code SharedAccountFinancialReportController} class manages the interaction between the view model
 * and the use case interactor for generating shared account financial reports. It extends the generic
 * {@link FinancialReportController} to handle specific input and state types for shared accounts.
 *
 * <p>This class is part of the interface adapters layer in Clean Architecture, facilitating the flow of
 * data between the user interface and the business logic for shared account financial reporting.</p>
 *
 * <p><b>Author:</b> Dana Huang, Eric Chen</p>
 */
public class SharedAccountFinancialReportController extends FinancialReportController<
        SharedAccountFinancialReportInputBoundary,
        SharedAccountFinancialReportViewModel,
        SharedAccountFinancialReportInputData,
        SharedAccountFinancialReportState> {

    /**
     * Constructs a {@code SharedAccountFinancialReportController} with the specified interactor and view model.
     *
     * @param sharedAccountFinancialReportInteractor the use case interactor for generating shared account financial reports
     * @param viewModel the view model associated with the shared account financial report
     */
    public SharedAccountFinancialReportController(SharedAccountFinancialReportInputBoundary
                                                          sharedAccountFinancialReportInteractor,
                                                  SharedAccountFinancialReportViewModel viewModel) {
        super(sharedAccountFinancialReportInteractor, viewModel);
    }

    /**
     * Executes the financial report generation process for the specified shared account identification.
     *
     * @param identification the unique identifier for the shared account
     */
    public void execute(String identification){
        SharedAccountFinancialReportInputData sharedAccountFinancialReportInputData = new SharedAccountFinancialReportInputData(
                identification
        );
        financialReportInteractor.execute(sharedAccountFinancialReportInputData);
        this.viewModel.resetState();
    }

}
