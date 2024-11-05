package interface_adaptors.financial_report.shared_account;

import interface_adaptors.financial_report.FinancialReportState;

/**
 * The {@code SharedAccountFinancialReportState} class represents the state of a financial report
 * for a shared account. It extends the {@link FinancialReportState} class to manage the
 * identification, report content, and no-transaction messages specifically for shared accounts.
 *
 * <p>This class is part of the interface adapters layer in Clean Architecture, where it serves
 * as a state container that the view model uses to store and update the financial report data
 * associated with a shared account.</p>
 *
 * <p><b>Author:</b> Dana Huang</p>
 */
public class SharedAccountFinancialReportState extends FinancialReportState {

    /**
     * Constructs a {@code SharedAccountFinancialReportState} with default values for
     * identification, report content, and no-transaction message.
     */
    public SharedAccountFinancialReportState() {
        super();
    }
}
