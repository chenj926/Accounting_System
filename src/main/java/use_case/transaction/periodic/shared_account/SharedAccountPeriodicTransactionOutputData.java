package use_case.transaction.periodic.shared_account;

import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.time.LocalDate;

/**
 * The SharedAccountPeriodicTransactionOutputData class represents the output data of a periodic transaction operation
 * that involves a shared account.
 * It includes details such as the transaction amount, start date, end date, period, description, date, new balance,
 * and the set of user IDs responsible for the transaction.
 * This class is used to encapsulate the results of processing periodic transactions for shared accounts,
 * whether they are inflows or outflows.
 *
 * <p>This class provides constructors to initialize the output data based on different types of transactions
 * (inflow and outflow), and it offers getter and setter methods for each field, allowing flexible access and modification of the data.</p>
 *
 * @see PeriodicTransaction
 * @see LocalDate
 *
 * author Eric
 * author Rita
 */
public class SharedAccountPeriodicTransactionOutputData extends PeriodicTransactionOutputData {

    /**
     * Constructs a SharedAccountPeriodicTransactionOutputData object for a periodic transaction with the specified details.
     *
     * @param periodicTransaction   the periodic transaction entity
     */
    public SharedAccountPeriodicTransactionOutputData(PeriodicTransaction periodicTransaction) {
        super(periodicTransaction);
    }
}
