package interface_adaptors.financial_report.shared_account;

import interface_adaptors.financial_report.FinancialReportViewModel;

/**
 * The {@code SharedAccountFinancialReportViewModel} class represents the view model for managing
 * the state and content of financial reports for shared accounts. It extends the generic
 * {@link FinancialReportViewModel} class, specifically using {@link SharedAccountFinancialReportState}
 * as the state type.
 *
 * <p>This class is part of the interface adapters layer in Clean Architecture, responsible for
 * holding the state of the shared account financial report and notifying any listeners of
 * changes to the report content.</p>
 *
 * <p><b>Author:</b> Dana Huang, Eric Chen</p>
 */
public class SharedAccountFinancialReportViewModel extends FinancialReportViewModel<SharedAccountFinancialReportState> {

    /**
     * Constructs a {@code SharedAccountFinancialReportViewModel} with the view name set to
     * "Shared Account Financial Report". Initializes the state to a new instance of
     * {@link SharedAccountFinancialReportState} and sets the initial report content to an empty string.
     */
    public SharedAccountFinancialReportViewModel() {
        super("Shared Account Financial Report");
        this.reportContent = "";
        this.state = new SharedAccountFinancialReportState();
    }

    /**
     * Gets the current state of the shared account financial report.
     *
     * @return the current state, which is an instance of {@link SharedAccountFinancialReportState}
     */
    @Override
    public SharedAccountFinancialReportState getState(){
        return this.state;
    }

    /**
     * Sets the current state of the shared account financial report.
     *
     * @param state the new state, which is an instance of {@link SharedAccountFinancialReportState}
     */
    @Override
    public void setState(SharedAccountFinancialReportState state){
        this.state = state;
    }

    /**
     * Resets the state to a new default instance of {@link SharedAccountFinancialReportState}.
     */
    @Override
    public void resetState() {
        setState(new SharedAccountFinancialReportState());
    }

    @Override
    public void firePropertyChange(){
        support.firePropertyChange("sharedAccountFinancialReport", null, this.state);
    }
}

