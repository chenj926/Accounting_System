package use_case.update_periodic_at_login.shared_account;

import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputData;

import java.time.LocalDate;

/**
 * The SharedAccountUpdatePeriodicAtLoginInputData class represents the input
 * data required for updating periodic
 * transactions involving a shared account upon user login.
 * <p>
 * It extends {@link AccountUpdatePeriodicAtLoginInputData} and includes the
 * user identification and the current
 * date of the login. This data is used to identify the user and to process the
 * periodic transaction updates
 * that need to be applied based on the login date.
 * </p>
 *
 * @author Jessica
 */
public class SharedAccountUpdatePeriodicAtLoginInputData extends AccountUpdatePeriodicAtLoginInputData {

    /**
     * Constructs a UserAccountUpdatePeriodicAtLoginInputData object with the
     * specified details.
     *
     * @param identification identification of the user
     * @param currentDate    date of the login
     */
    public SharedAccountUpdatePeriodicAtLoginInputData(String identification, LocalDate currentDate) {
        super(identification, currentDate);
    }
}
