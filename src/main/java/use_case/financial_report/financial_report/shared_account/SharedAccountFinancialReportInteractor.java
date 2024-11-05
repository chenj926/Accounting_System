package use_case.financial_report.financial_report.shared_account;

import data_access.account.shared_account.SharedAccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import entity.transaction.Transaction;
import use_case.financial_report.financial_report.FinancialReportInteractor;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;

import java.util.List;
import java.util.Set;

/**
 * Interactor class for generating financial reports for shared accounts.
 * <p>
 * This class extends {@link FinancialReportInteractor} and implements the {@link SharedAccountFinancialReportInputBoundary}
 * interface to handle the logic for generating financial reports specific to shared accounts. It retrieves and processes
 * the relevant data and prepares the output for presentation.
 * </p>
 *
 * @author Eric
 * @author Dana
 */
public class SharedAccountFinancialReportInteractor extends FinancialReportInteractor<
        SharedAccountDataAccessInterface,
        SharedAccount,
        SharedAccountOneTimeTransactionOutputData,
        SharedAccountPeriodicTransactionOutputData,
        SharedAccountFinancialReportInputData,
        SharedAccountFinancialReportOutputBoundary,
        SharedAccountFinancialReportOutputData
        > implements SharedAccountFinancialReportInputBoundary{

    /**
     * Constructs a new {@code SharedAccountFinancialReportInteractor}.
     * <p>
     * This constructor initializes the interactor with the necessary components for accessing shared account data,
     * generating the financial report, and presenting the output.
     * </p>
     *
     * @param account the shared account for which the financial report will be generated
     * @param presenter the presenter responsible for preparing the output view
     * @param userDataAccessObject the data access object used to retrieve account and transaction data
     */
    public SharedAccountFinancialReportInteractor(SharedAccount account,
                                                  SharedAccountFinancialReportOutputBoundary presenter,
                                                  SharedAccountDataAccessInterface userDataAccessObject) {
        super(account, presenter, userDataAccessObject);
    }

    /**
     * Generates the content of the financial report based on the input data.
     * <p>
     * This method retrieves the shared account data and transactions, formats the report, and returns it as a string.
     * If no transactions are found, it triggers the failure view preparation.
     * </p>
     *
     * @param inputData the input data required for generating the financial report
     * @return the content of the financial report as a string
     */
    @Override
    protected String generateReportContent(SharedAccountFinancialReportInputData inputData) {
        String id = inputData.getIdentification();
        this.account = userDataAccessObject.getById(id);
        String shareId = this.account.getIdentification();
        Set<String> userIds = this.account.getSharedUserIdentifications();
        String totalIncome = String.valueOf(this.account.getTotalIncome());
        String totalOutflow = String.valueOf(this.account.getTotalOutflow());
        String totalBalance = String.valueOf(this.account.getTotalBalance());

        StringBuilder report = new StringBuilder();
        report.append("Financial Report for Account: ").append(shareId + ": \n" + userIds.toString()).append("\n");
        report.append("\tTotal Income: ").append(totalIncome).append("\n");
        report.append("\tTotal Outflow: ").append(totalOutflow).append("\n");
        report.append("\tTotal Balance: ").append(totalBalance).append("\n");
        report.append("Transactions: \n");
        report.append("------------------------------------------------------------------\n");
        report.append("| User(id) | Date | Amount | Category | Description |\n");
        report.append("------------------------------------------------------------------\n");

        List<Transaction> transactions = this.userDataAccessObject.readTransactions(id);
        if (transactions.isEmpty()) {
            this.presenter.prepareFailView(report.toString()+"\nNo transactions yet!");
            return "No transactions yet!";
        }

        for (Transaction transaction : transactions) {
            String[] transactionIds = transaction.getIdentification().split(";");
            report.append(transactionIds[1]).append(" | ")
                    .append(transaction.getDate()).append(" | ")
                    .append(transaction.getAmount()).append(" -- ")
                    .append(transaction.getTransactionCategory()).append(" : ")
                    .append(transaction.getDescription()).append("\n");
        }

        return report.toString();
    }

    /**
     * Creates the output data object for the shared account financial report based on the report content.
     * <p>
     * This method returns a new instance of {@link SharedAccountFinancialReportOutputData} containing the generated
     * report content.
     * </p>
     *
     * @param reportContent the content of the financial report
     * @return a {@link SharedAccountFinancialReportOutputData} object containing the report content
     */
    @Override
    protected SharedAccountFinancialReportOutputData createOutputData(String reportContent) {
        return new SharedAccountFinancialReportOutputData(reportContent);
    }
}
