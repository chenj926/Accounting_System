package interface_adaptors.financial_report.user_account;

import data_access.financial_report_api_accessor.TextToSpeech;
import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.FinancialReportPresenter;
import use_case.financial_report.financial_report.user_account.UserAccountFinancialReportOutputBoundary;
import use_case.financial_report.financial_report.user_account.UserAccountFinancialReportOutputData;

/**
 * The {@code UserAccountFinancialReportPresenter} class implements the output boundary for presenting
 * the financial report of a user account. This class extends the {@link FinancialReportPresenter}
 * and handles the preparation of both success and failure views for the financial report.
 *
 * <p>This presenter is part of the interface adapters layer in Clean Architecture. It takes the
 * output data from the use case interactor, updates the view model, and triggers the necessary
 * UI updates through the {@link ViewManagerModel}. Additionally, this presenter includes functionality
 * to convert the financial report content to speech using the {@link TextToSpeech} class.</p>
 *
 * <p><b>Authors:</b> Chi Feng Huang, Eric Chen</p>
 */
public class UserAccountFinancialReportPresenter extends FinancialReportPresenter<
        UserAccountFinancialReportViewModel,
        UserAccountFinancialReportState,
        UserAccountFinancialReportOutputData> implements UserAccountFinancialReportOutputBoundary {

    /**
     * Constructs a {@code UserAccountFinancialReportPresenter} with the specified view model
     * and view manager.
     *
     * @param viewModel  the view model responsible for storing and managing the financial report state
     * @param viewManager the view manager responsible for managing view transitions
     */
    public UserAccountFinancialReportPresenter(UserAccountFinancialReportViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares and presents the financial report by storing its content in the view model,
     * updating the view, and optionally converting the report content to speech.
     *
     * @param outputData the output data containing the financial report content
     */
    @Override
    public void prepareSuccessView(UserAccountFinancialReportOutputData outputData) {
        UserAccountFinancialReportState state = this.viewModel.getState();
        this.reportContent = outputData.getReportContent();

        state.setReportContent(reportContent);
        state.setNoTransaction(null);  // reset the no transaction error
        this.viewModel.setState(state);
        this.viewModel.setReportContent(state.getReportContent());
        this.viewModel.firePropertyChange();

        this.viewManager.setActiveViewName(viewModel.getViewName());
    }

    /**
     * Prepares and presents a failure view indicating that there are no transactions
     * available for the financial report.
     *
     * @param noTransaction the message indicating no available transactions
     */
    @Override
    public void prepareFailView(String noTransaction) {
        UserAccountFinancialReportState state = this.viewModel.getState();

        state.setReportContent(noTransaction);
        state.setNoTransaction(noTransaction);
        this.viewModel.setState(state);
        this.viewModel.setReportContent(state.getReportContent());
        this.viewModel.firePropertyChange();

        this.viewManager.setActiveViewName(viewModel.getViewName());
    }
}




