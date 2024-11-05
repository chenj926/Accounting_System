package use_case.transaction.periodic.user_account;

import entity.transaction.periodic.PeriodicInflow;
import entity.transaction.periodic.PeriodicOutflow;
import entity.transaction.periodic.PeriodicTransaction;
import use_case.transaction.periodic.PeriodicTransactionOutputData;

import java.time.LocalDate;

/**
 * The UserAccountPeriodicTransactionOutputData class represents the output data of a periodic transaction operation.
 * It includes details such as the transaction amount, start date, end date, period, description, date, and new balance.
 * This class is used to encapsulate the results of processing periodic transactions, whether they are inflows or outflows.
 * The data captured in this class helps to track and manage the financial transactions over specified periods.
 *
 * <p>This class provides constructors to initialize the output data based on different types of transactions
 * (inflow and outflow), and it offers getter and setter methods for each field, allowing flexible access and modification of the data.</p>
 *
 * @see PeriodicInflow
 * @see PeriodicOutflow
 * @see LocalDate
 *
 * @author Eric
 * @author Dana
 * @author Jessica
 */
public class UserAccountPeriodicTransactionOutputData extends PeriodicTransactionOutputData {

    /**
     * Constructs a UserAccountPeriodicTransactionOutputData object for an inflow transaction with the specified details.
     *
     * @param periodicTransaction the periodic transaction entity
     */
    public UserAccountPeriodicTransactionOutputData(PeriodicTransaction periodicTransaction) {
        super(periodicTransaction);
    }
}

