package interface_adaptors.financial_report.user_account;

import interface_adaptors.financial_report.FinancialReportState;

/**
 * The {@code UserAccountFinancialReportState} class represents the state of a financial report
 * specific to a user account. This class extends {@link FinancialReportState} and inherits its
 * properties, providing a concrete implementation tailored for user account financial reporting.
 *
 * <p>This state class is part of the interface adapters layer in the Clean Architecture, where it
 * is used to manage and store the current state of a financial report related to a user account.
 * It interacts with the view model and controllers to ensure that the data presented in the view
 * is accurate and up to date.</p>
 *
 * <p><b>Author:</b> Dana Huang</p>
 */
public class UserAccountFinancialReportState extends FinancialReportState {

    /**
     * Constructs a {@code UserAccountFinancialReportState} with default values.
     * <p>
     * This constructor initializes the financial report state with default, empty values
     * for all fields, preparing it to store specific data related to a user account financial report.
     * </p>
     */
    public UserAccountFinancialReportState() {
        super();
    }
}
