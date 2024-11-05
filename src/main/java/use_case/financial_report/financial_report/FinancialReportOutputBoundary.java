package use_case.financial_report.financial_report;

/**
 * Output boundary interface for presenting financial reports.
 * <p>
 * This interface defines methods for preparing views in the case of successful or failed financial report generation.
 * Implementations of this interface are responsible for handling the output data of financial report use cases.
 * </p>
 *
 * @param <T> the type of the report output data
 *
 * @author Dana
 */
public interface FinancialReportOutputBoundary<T> {

    /**
     * Prepares the view for a successful financial report generation.
     * <p>
     * This method is called when the financial report has been generated successfully. It prepares the output data
     * for presentation.
     * </p>
     *
     * @param reportOutputData the output data of the financial report
     */
    void prepareSuccessView(T reportOutputData);

    /**
     * Prepares the view for a failed financial report generation.
     * <p>
     * This method is called when there is an error in generating the financial report. It prepares the error message
     * for presentation.
     * </p>
     *
     * @param error the error message describing the failure
     */
    void prepareFailView(String error);
}
