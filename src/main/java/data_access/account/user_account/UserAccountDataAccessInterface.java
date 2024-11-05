package data_access.account.user_account;

import data_access.account.AccountDataAccessInterface;
import entity.account.user_account.UserAccount;
import org.springframework.stereotype.Service;
import use_case.transaction.one_time.user_account.UserAccountOneTimeTransactionOutputData;
import use_case.transaction.periodic.user_account.UserAccountPeriodicTransactionOutputData;

/**
 * Interface for data access operations related to user accounts.
 * <p>
 * This interface provides methods to manage user accounts and transactions, including
 * checking existence, retrieving, updating, deleting, and saving user accounts,
 * as well as saving transaction data.
 * </p>
 *
 * @author Eric
 * @author Jessica
 * @author Dana
 */
@Service
public interface UserAccountDataAccessInterface
        extends AccountDataAccessInterface<UserAccount, UserAccountOneTimeTransactionOutputData, UserAccountPeriodicTransactionOutputData> {
}
