package use_case.update_periodic_at_login.user_account;

import org.springframework.stereotype.Service;
import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputBoundary;

/**
 * The UserAccountUpdatePeriodicAtLoginInputBoundary interface provides a method
 * for executing periodic transaction updates.
 * Implementations of this interface will handle the update using the provided
 * input data.
 *
 * @author Jessica
 */
@Service
public interface UserAccountUpdatePeriodicAtLoginInputBoundary
        extends AccountUpdatePeriodicAtLoginInputBoundary<UserAccountUpdatePeriodicAtLoginInputData> {
}
