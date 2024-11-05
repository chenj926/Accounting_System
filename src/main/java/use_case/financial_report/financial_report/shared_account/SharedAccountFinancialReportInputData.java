package use_case.financial_report.financial_report.shared_account;

import use_case.financial_report.financial_report.FinancialReportInputData;

/**
 * Input data for generating financial reports.
 *
 * @author Dana
 */
public class SharedAccountFinancialReportInputData extends FinancialReportInputData {

    /**
     * Constructs a FinancialReportInputData object.
     *
     * @param identification the account ID
     */
    public SharedAccountFinancialReportInputData(String identification) {
        super(identification);
    }
}
