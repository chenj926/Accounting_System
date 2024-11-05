package use_case.financial_report.financial_report.user_account;

import use_case.financial_report.financial_report.FinancialReportOutputData;

/**
 * Output data for presenting financial reports.
 *
 * @author Chi Fong
 * @author Dana
 */
public class UserAccountFinancialReportOutputData extends FinancialReportOutputData {

    /**
     * Constructs a FinancialReportOutputData object.
     *
     * @param reportContent the content of the financial report
     */
    public UserAccountFinancialReportOutputData(String reportContent) {
        super(reportContent);
    }

}


