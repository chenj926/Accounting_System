package use_case.update_periodic_at_login.user_account;

import org.springframework.stereotype.Service;
import use_case.update_periodic_at_login.AccountUpdatePeriodicAtLoginInputData;

import java.time.LocalDate;

/**
 * The UserAccountUpdatePeriodicAtLoginInputData class represents the input data
 * required for updating periodic transaction at login operation.
 * It includes details such as the user identification and the current date.
 *
 * @author Jessica
 */
@Service
public class UserAccountUpdatePeriodicAtLoginInputData extends AccountUpdatePeriodicAtLoginInputData {
    /**
     * Constructs a UserAccountUpdatePeriodicAtLoginInputData object with the
     * specified details.
     *
     * @param identification identification of the user
     * @param currentDate    date of the login
     */
    public UserAccountUpdatePeriodicAtLoginInputData(String identification, LocalDate currentDate) {
        super(identification, currentDate);
    }
}
