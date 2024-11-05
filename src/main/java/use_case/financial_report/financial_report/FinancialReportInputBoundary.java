package use_case.financial_report.financial_report;

/**
 * Input boundary interface for processing financial reports.
 * <p>
 * This interface defines a method for executing financial report use cases. Implementations of this interface
 * are responsible for handling the input data and triggering the use case logic.
 * </p>
 *
 * @param <T> the type of the input data for the financial report use case
 *
 * @author Dana
 */
public interface FinancialReportInputBoundary<T> {

    /**
     * Executes the financial report use case with the provided input data.
     * <p>
     * This method processes the input data and triggers the logic to generate a financial report.
     * </p>
     *
     * @param inputData the input data required for generating the financial report
     */
    void execute(T inputData);
}
