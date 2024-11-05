package use_case.financial_report.financial_report.user_account;

import data_access.account.user_account.UserAccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import entity.transaction.Transaction;
import use_case.financial_report.financial_report.FinancialReportInteractor;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;


import java.util.List;

/**
 * Handles the logic for generating financial reports.
 *<p>
 * Creates a financial report based on account transactions, income, outflow, and balance.
 * Formats the report and sends it to the output boundary for presentation.
 *</p>
 * @see UserAccountFinancialReportInputBoundary
 * @see UserAccountFinancialReportOutputBoundary
 * @see UserAccountDataAccessInterface
 * @see UserAccount
 * @see Transaction
 *
 * @author Chi Fong
 * @author Eric
 * @author Dana
 */
public class UserAccountFinancialReportInteractor extends FinancialReportInteractor<
        UserAccountDataAccessInterface,
        UserAccount,
        UserAccountOneTimeTransactionOutputData,
        UserAccountPeriodicTransactionOutputData,
        UserAccountFinancialReportInputData,
        UserAccountFinancialReportOutputBoundary,
        UserAccountFinancialReportOutputData
        > implements UserAccountFinancialReportInputBoundary {

    /**
     * Constructs a FinancialReportInteractor with the specified account, output boundary, and data access interface.
     *
     * @param account the account for which the financial report is to be generated
     * @param presenter the output boundary to which the generated report will be presented
     * @param userAccountDataAccessInterface the data access interface for retrieving user account data
     */
    public UserAccountFinancialReportInteractor(UserAccount account,
                                                UserAccountFinancialReportOutputBoundary presenter,
                                                UserAccountDataAccessInterface userAccountDataAccessInterface) {
        super(account, presenter, userAccountDataAccessInterface);
    }

    /**
     * Generates the content of the financial report as a string.
     *
     * This method collects the account's username, total income, total outflow, total balance,
     * and all transactions to create a comprehensive financial report.
     *
     * @return the content of the financial report
     */
    @Override
    protected String generateReportContent(UserAccountFinancialReportInputData inputData) {
        String id = inputData.getIdentification();
        //debug
        this.account = userDataAccessObject.getById(id);
        String username = this.account.getUsername();
        String totalIncome = String.valueOf(this.account.getTotalIncome());
        String totalOutflow = String.valueOf(this.account.getTotalOutflow());
        String totalBalance = String.valueOf(this.account.getTotalBalance());

        StringBuilder report = new StringBuilder();
        report.append("Financial Report for Account: ").append(username + ": " + id).append("\n");
        report.append("\tTotal Income: ").append(totalIncome).append("\n");
        report.append("\tTotal Outflow: ").append(totalOutflow).append("\n");
        report.append("\tTotal Balance: ").append(totalBalance).append("\n");
        report.append("Transactions: \n");
        report.append("--------------------------------------------------------\n");
        report.append("| Date | Amount | Category | Description |\n");
        report.append("--------------------------------------------------------\n");

        List<Transaction> transactions = this.userDataAccessObject.readTransactions(id);
        if (transactions.isEmpty()) {
            this.presenter.prepareFailView(report.toString()+"\nNo transactions yet!");
            return "No transactions yet!";
        }

        for (Transaction transaction : transactions) {
            report.append(transaction.getDate()).append(" | ")
                    .append(transaction.getAmount()).append(" -- ")
                    .append(transaction.getTransactionCategory()).append(" : ")
                    .append(transaction.getDescription()).append("\n");
        }

        return report.toString();
    }

    /**
     * Creates the output data object for the user account financial report based on the report content.
     * <p>
     * This method returns a new instance of {@link UserAccountFinancialReportOutputData} containing the generated
     * report content.
     * </p>
     *
     * @param reportContent the content of the financial report
     * @return a {@link UserAccountFinancialReportOutputData} object containing the report content
     */
    @Override
    protected UserAccountFinancialReportOutputData createOutputData(String reportContent) {
        return new UserAccountFinancialReportOutputData(reportContent);
    }
}









