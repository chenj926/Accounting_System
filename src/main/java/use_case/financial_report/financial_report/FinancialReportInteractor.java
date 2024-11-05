package use_case.financial_report.financial_report;

import data_access.account.AccountDataAccessInterface;
import entity.account.Account;
import use_case.transaction.one_time.OneTimeTransactionOutputData;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

/**
 * Abstract interactor class for generating financial reports.
 * <p>
 * This class defines the core logic for generating financial reports by interacting with account data
 * and preparing the output view. It follows the input/output boundary pattern and is intended to be extended
 * by specific implementations for different types of accounts and reports.
 * </p>
 *
 * @param <DAO> the type of the data access object used to retrieve account and transaction data
 * @param <A> the type of the account
 * @param <O> the type of the one-time transaction output data
 * @param <P> the type of the periodic transaction output data
 * @param <I> the type of the input data required for generating the financial report
 * @param <PRE> the type of the output boundary for preparing the view
 * @param <FO> the type of the output data for the financial report
 *
 * @author Eric
 * @author Chi Fong
 */
public abstract class FinancialReportInteractor<
        DAO extends AccountDataAccessInterface<A, O, P>,
        A extends Account,
        O extends OneTimeTransactionOutputData,
        P extends PeriodicTransactionOutputData,
        I extends FinancialReportInputData,
        PRE extends FinancialReportOutputBoundary<FO>,
        FO extends FinancialReportOutputData> {

    protected final DAO userDataAccessObject;
    protected final PRE presenter;
    protected A account;

    public FinancialReportInteractor(A account,
                                     PRE presenter,
                                     DAO userDataAccessObject) {
        this.account = account;
        this.presenter = presenter;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(I inputData) {
        String reportContent = generateReportContent(inputData);
        // if there is at least 1 transaction
        if (!reportContent.equals("No transactions yet!")) {
            FO outputData = this.createOutputData(reportContent);
            this.presenter.prepareSuccessView(outputData);
        }
    }

    protected abstract String generateReportContent(I inputData);

    protected abstract FO createOutputData(String reportContent);
}
