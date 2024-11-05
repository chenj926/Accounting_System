package use_case.update_periodic_at_login.shared_account;

import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputBoundary;

/**
 * The SharedAccountUpdatePeriodicAtLoginInputBoundary interface defines the
 * contract for updating periodic transactions
 * for shared accounts upon user login.
 * <p>
 * This interface extends {@link AccountUpdatePeriodicAtLoginInputBoundary} and
 * works specifically with
 * {@link SharedAccountUpdatePeriodicAtLoginInputData} to handle the update
 * process for shared account periodic transactions.
 * </p>
 *
 * @author Jessica
 */
public interface SharedAccountUpdatePeriodicAtLoginInputBoundary
        extends AccountUpdatePeriodicAtLoginInputBoundary<SharedAccountUpdatePeriodicAtLoginInputData> {
}
