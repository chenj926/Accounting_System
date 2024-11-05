package use_case.financial_report.financial_report;

/**
 * Output data for presenting financial reports.
 *
 * @author Dana
 * @author Chi Fong
 */
public abstract class FinancialReportOutputData {
    private final String reportContent;

    /**
     * Constructs a FinancialReportOutputData object.
     *
     * @param reportContent the content of the financial report
     */
    public FinancialReportOutputData(String reportContent) {

        this.reportContent = reportContent;
    }

    /**
     * Returns the content of the financial report.
     *
     * @return the report content
     */
    public String getReportContent() { return reportContent; }
}
