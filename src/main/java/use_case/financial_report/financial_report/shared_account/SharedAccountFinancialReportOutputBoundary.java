package use_case.financial_report.financial_report.shared_account;

import use_case.financial_report.financial_report.FinancialReportOutputBoundary;

/**
 * Output boundary interface for presenting shared account financial reports.
 * <p>
 * This interface extends {@link FinancialReportOutputBoundary} and specifies the output type as
 * {@link SharedAccountFinancialReportOutputData}. It is used to prepare the view for shared account financial reports.
 * </p>
 *
 * @see SharedAccountFinancialReportOutputData
 * @see FinancialReportOutputBoundary
 *
 * @author Dana
 */
public interface SharedAccountFinancialReportOutputBoundary extends FinancialReportOutputBoundary<SharedAccountFinancialReportOutputData> {
}
