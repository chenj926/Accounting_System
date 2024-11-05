package use_case.financial_report.financial_report.shared_account;

import use_case.financial_report.financial_report.FinancialReportOutputData;

/**
 * Output data for presenting financial reports.
 *
 * @author Dana
 */
public class SharedAccountFinancialReportOutputData extends FinancialReportOutputData {

    /**
     * Constructs a FinancialReportOutputData object.
     *
     * @param reportContent the content of the financial report
     */
    public SharedAccountFinancialReportOutputData(String reportContent) {
        super(reportContent);
    }
}
