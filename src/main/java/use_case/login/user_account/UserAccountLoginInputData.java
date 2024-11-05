package use_case.login.user_account;

import org.springframework.stereotype.Service;
import use_case.login.LoginInputData;

/**
 * The LoginInputData class represents the input data required for a login operation.
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
@Service
public class UserAccountLoginInputData extends LoginInputData {

    /**
     * Constructs a LoginInputData object with the specified password and identification.
     *
     * @param password       the identification for the login
     * @param identification the password for the login
     */
    public UserAccountLoginInputData(String identification, String password){
        super(identification,password);
    }

    // Default constructor for JSON deserialization
    public UserAccountLoginInputData() {
        super(null, null);
    }
}