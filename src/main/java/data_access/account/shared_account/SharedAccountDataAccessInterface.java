package data_access.account.shared_account;

import data_access.account.AccountDataAccessInterface;
import entity.account.shared_account.SharedAccount;
import use_case.transaction.one_time.shared_account.SharedAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.shared_account.SharedAccountPeriodicTransactionOutputData;

/**
 * Interface for data access operations related to shared accounts.
 * <p>
 * This interface provides methods to manage shared accounts, including checking
 * existence and saving shared accounts.
 * </p>
 *
 * @author Eric
 */
public interface SharedAccountDataAccessInterface extends
        AccountDataAccessInterface<SharedAccount,
        SharedAccountOneTimeTransactionOutputData,
        SharedAccountPeriodicTransactionOutputData> {
}