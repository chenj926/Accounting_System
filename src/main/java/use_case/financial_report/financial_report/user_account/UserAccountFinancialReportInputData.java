package use_case.financial_report.financial_report.user_account;

import use_case.financial_report.financial_report.FinancialReportInputData;

/**
 * Input data for generating financial reports.
 *
 * @author Chi Fong
 * @author Dana
 */
public class UserAccountFinancialReportInputData extends FinancialReportInputData {

    /**
     * Constructs a FinancialReportInputData object.
     *
     * @param identification the account ID
     */

    public UserAccountFinancialReportInputData(String identification) {
        super(identification);
    }
}


