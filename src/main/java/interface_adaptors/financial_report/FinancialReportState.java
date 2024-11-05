package interface_adaptors.financial_report;

/**
 * The {@code FinancialReportState} class represents the state of a financial report in the system.
 * It stores information related to the identification of the report, the content of the report,
 * and a message indicating when no transactions are present.
 * <p>
 * This class serves as an abstract base class in the Clean Architecture, providing a standardized
 * structure for managing the state of financial reports in the application.
 * </p>
 *
 * <p><b>Author:</b> Dana Hunag</p>
 */
public abstract class FinancialReportState {
    private String identification;
    private String reportContent;
    private String noTransaction;

    /**
     * Constructs a {@code FinancialReportState} object with default values.
     * <p>
     * Initializes the state with empty strings for identification, report content, and the no transaction message.
     * </p>
     */
    public FinancialReportState(){
    this.identification = "";
    this.reportContent = "";
    this.noTransaction = "";
    }

    /**
     * Gets the identification of the report.
     * <p>
     * This identification could represent a transaction ID or any other identifier associated with the report.
     * </p>
     *
     * @return the identification of the report
     */
    public String getIdentification() {
        return this.identification;
    }
    /**
     * Sets the identification of the report.
     * <p>
     * This method allows for updating the identifier associated with the report.
     * </p>
     *
     * @param identification the new identification for the report
     */
    public void setIdentification(String identification) {
        this.identification = identification;
    }

    /**
     * Gets the content of the financial report.
     * <p>
     * This content typically includes details about the transactions or financial activities documented in the report.
     * </p>
     *
     * @return the content of the report
     */
    public String getReportContent() {
        return this.reportContent;
    }
    /**
     * Sets the content of the financial report.
     * <p>
     * This method allows for updating the detailed content of the report, which may include transactions or other financial data.
     * </p>
     *
     * @param reportContent the new content for the report
     */
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    /**
     * Gets the message indicating that no transactions are present in the report.
     * <p>
     * This message is typically used to inform the user when there are no transactions to display.
     * </p>
     *
     * @return the message indicating no transactions
     */
    public String getNoTransaction() {
        return this.noTransaction;
    }

    /**
     * Sets the message indicating that no transactions are present in the report.
     * <p>
     * This method allows for updating the message shown when there are no transactions to include in the report.
     * </p>
     *
     * @param noTransaction the new message indicating no transactions
     */
    public void setNoTransaction(String noTransaction) {
        this.noTransaction = noTransaction;
    }
}
