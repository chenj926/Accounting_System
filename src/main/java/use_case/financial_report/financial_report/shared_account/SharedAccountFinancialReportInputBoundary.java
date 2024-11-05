package use_case.financial_report.financial_report.shared_account;

import use_case.financial_report.financial_report.FinancialReportInputBoundary;

/**
 * Input boundary interface for processing shared account financial reports.
 * <p>
 * This interface extends {@link FinancialReportInputBoundary} and specifies the input type as
 * {@link SharedAccountFinancialReportInputData}. It is used to trigger the use case logic for generating
 * financial reports related to shared accounts.
 * </p>
 *
 * @see SharedAccountFinancialReportInputData
 * @see FinancialReportInputBoundary
 *
 * @author Dana
 */
public interface SharedAccountFinancialReportInputBoundary extends FinancialReportInputBoundary<SharedAccountFinancialReportInputData> {
}
