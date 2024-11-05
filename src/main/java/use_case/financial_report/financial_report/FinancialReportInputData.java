package use_case.financial_report.financial_report;

import java.util.Date;

/**
 * Input data for generating financial reports.
 *
 * @author Dana
 */
public abstract class FinancialReportInputData {
    private final String identification;


    /**
     * Constructs a FinancialReportInputData object.
     *
     * @param identification the account ID
     */
    public FinancialReportInputData(String identification) {
        this.identification = identification;
    }

    /**
     * Returns the account ID.
     *
     * @return the account ID
     */
    public String getIdentification() {
        return this.identification;
    }
}
