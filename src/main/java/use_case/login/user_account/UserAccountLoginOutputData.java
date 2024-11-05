package use_case.login.user_account;

import org.springframework.stereotype.Service;
import use_case.login.LoginOutputData;

/**
 * The LoginOutputData class represents the output data of a login operation.
 * It includes the identification of the user and the success status of the login attempt.
 *
 * @author Dana
 * @author Eric
 * @author Jessica
 */
@Service
public class UserAccountLoginOutputData extends LoginOutputData {

    /**
     * Constructs a LoginOutputData object with the specified identification and success status.
     *
     * @param identification the identification of the user
     * @param success        the success status of the login attempt
     */
    public UserAccountLoginOutputData(String identification, boolean success) {
        super(identification, success);

    }
}