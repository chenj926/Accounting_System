package interface_adaptors.financial_report.user_account;

import interface_adaptors.financial_report.FinancialReportViewModel;

/**
 * The {@code UserAccountFinancialReportViewModel} class is responsible for managing the state
 * and content of a financial report specific to a user account. It extends the generic
 * {@link FinancialReportViewModel} class, providing a concrete implementation for user account
 * financial reports.
 *
 * <p>This view model plays a critical role in the interface adapters layer of the Clean Architecture,
 * acting as an intermediary between the UI (view) and the application's business logic. It
 * manages the {@link UserAccountFinancialReportState}, handles updates to the report content,
 * and notifies any registered listeners of changes to the state.</p>
 *
 * <p><b>Authors:</b> Eric Chen, Dana Huang</p>
 */
public class UserAccountFinancialReportViewModel extends FinancialReportViewModel<
        UserAccountFinancialReportState> {

    /**
     * Constructs a {@code UserAccountFinancialReportViewModel} object with the view name
     * set to "Financial Report". This constructor initializes the report content as empty
     * and sets the state to a new instance of {@link UserAccountFinancialReportState}.
     */
    public UserAccountFinancialReportViewModel() {
        super("Financial Report");
        this.reportContent = "";
        this.state = new UserAccountFinancialReportState();
    }

    /**
     * Gets the current state of the financial report.
     *
     * @return the current {@link UserAccountFinancialReportState} instance
     */
    @Override
    public UserAccountFinancialReportState getState() {
        return this.state;
    }

    // setters
    /**
     * Sets the current state of the financial report and updates the report content accordingly.
     *
     * @param state the new {@link UserAccountFinancialReportState} to set
     */
    @Override
    public void setState(UserAccountFinancialReportState state) {
        this.state = state;
    }

    /**
     * Resets the state to a new default {@link UserAccountFinancialReportState}.
     * This method is useful for clearing or reinitializing the state when needed.
     */
    @Override
    public void resetState() {
        UserAccountFinancialReportState newState = new UserAccountFinancialReportState();
        setState(newState);
    }

    @Override
    public void firePropertyChange(){
        support.firePropertyChange("userAccountFinancialReport", null, this.state);
    }
}
