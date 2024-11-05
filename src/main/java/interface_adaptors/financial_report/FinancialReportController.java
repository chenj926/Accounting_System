package interface_adaptors.financial_report;

import use_case.financial_report.financial_report.FinancialReportInputBoundary;
import use_case.financial_report.financial_report.FinancialReportInputData;

/**
 * The {@code FinancialReportController} class serves as an abstract controller in the Clean Architecture,
 * responsible for handling user input related to financial reports and coordinating the interaction
 * between the view model and the use case interactor.
 * <p>
 * This class is parameterized to allow flexibility in the specific types used for the input boundary,
 * view model, input data, and state. It encapsulates the core logic for managing financial reports,
 * ensuring separation of concerns and promoting testability.
 * </p>
 *
 * <p><b>Authors:</b> Eric Chen, Dana Hunag</p>
 *
 * @param <IB> the type of the input boundary that handles the use case logic for financial reports
 * @param <V>  the type of the view model that represents the data needed by the view
 * @param <I>  the type of the input data used by the use case interactor
 * @param <S>  the type of the state that represents the current state of the financial report
 */
public abstract class FinancialReportController<
        IB extends FinancialReportInputBoundary<I>,
        V extends FinancialReportViewModel<S>,
        I extends FinancialReportInputData,
        S extends FinancialReportState> {

    protected final IB financialReportInteractor;
    protected final V viewModel;

    /**
     * Constructs a {@code FinancialReportController} with the specified interactor and view model.
     * <p>
     * This constructor initializes the controller with the necessary components to handle financial report operations.
     * </p>
     *
     * @param financialReportInteractor the interactor responsible for executing the use case logic
     * @param viewModel                 the view model that holds the data to be displayed in the view
     */
    public FinancialReportController (IB financialReportInteractor, V viewModel){
        this.financialReportInteractor = financialReportInteractor;
        this.viewModel = viewModel;
    }
}
