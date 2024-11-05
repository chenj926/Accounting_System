package use_case.update_periodic_at_login;

/**
 * The AccountUpdatePeriodicAtLoginInputBoundary interface defines the contract for
 * executing the periodic transaction update process during a user login.
 * <p>
 * Implementations of this interface should handle the logic for updating periodic transactions
 * based on the input data provided when a user logs into their account. This might involve
 * processing and saving transactions that should have occurred since the last login.
 * </p>
 *
 * @param <D> the type of input data required for updating periodic transactions
 * @see AccountUpdatePeriodicAtLoginInputData
 *
 * @author Jessica
 */
public interface AccountUpdatePeriodicAtLoginInputBoundary<D extends AccountUpdatePeriodicAtLoginInputData> {
    /**
     * Executes the periodic transaction update process with the given input data.
     *
     * @param accountUpdatePeriodicAtLoginInputData the input data required for the update process
     */
    void execute(D accountUpdatePeriodicAtLoginInputData);
}
