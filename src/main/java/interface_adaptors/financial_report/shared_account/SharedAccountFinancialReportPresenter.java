package interface_adaptors.financial_report.shared_account;

import interface_adaptors.ViewManagerModel;
import interface_adaptors.financial_report.FinancialReportPresenter;
import use_case.financial_report.financial_report.shared_account.SharedAccountFinancialReportOutputBoundary;
import use_case.financial_report.financial_report.shared_account.SharedAccountFinancialReportOutputData;

/**
 * The {@code SharedAccountFinancialReportPresenter} class is responsible for presenting the financial report
 * for shared accounts. It interacts with the view model to update the state and manage the report content,
 * and it also uses the Text-to-Speech API to vocalize the report.
 *
 * <p>This class implements the {@link SharedAccountFinancialReportOutputBoundary} interface, ensuring that
 * the output data from the use case is correctly handled and reflected in the view.</p>
 *
 * <p><b>Author:</b> Eric Chen</p>
 */
public class SharedAccountFinancialReportPresenter extends FinancialReportPresenter<
        SharedAccountFinancialReportViewModel,
        SharedAccountFinancialReportState,
        SharedAccountFinancialReportOutputData> implements SharedAccountFinancialReportOutputBoundary {

    /**
     * Constructs a {@code SharedAccountFinancialReportPresenter} with the specified view model and view manager.
     *
     * @param viewModel  the view model associated with the shared account financial report
     * @param viewManager the view manager for handling view transitions
     */
    public SharedAccountFinancialReportPresenter(SharedAccountFinancialReportViewModel viewModel, ViewManagerModel viewManager) {
        super(viewModel, viewManager);
    }

    /**
     * Prepares the success view by updating the state with the financial report content
     * and using Text-to-Speech to vocalize the report.
     *
     * @param outputData the output data containing the financial report content
     */
    @Override
    public void prepareSuccessView(SharedAccountFinancialReportOutputData outputData) {
        SharedAccountFinancialReportState state = this.viewModel.getState();
        this.reportContent = outputData.getReportContent();

        state.setReportContent(reportContent);
        state.setNoTransaction(null);  // reset the no transaction error
        this.viewModel.setState(state);
        this.viewModel.setReportContent(state.getReportContent());
        this.viewModel.firePropertyChange();

        this.viewManager.setActiveViewName(viewModel.getViewName());
//
//        TextToSpeech TTS = new TextToSpeech();
//        TTS.speak(reportContent);
    }
}
