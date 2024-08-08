package use_case.financial_report;

/**
 * Input boundary for generating financial reports.
 *
 * @author : Chi Fong
 */
public interface UserAccountFinancialReportInputBoundary {
    /**
     * Generates a financial report.
     *
//     * @param inputData the input data for the report
     */
    void execute(UserAccountFinancialReportInputData inputData);
}


