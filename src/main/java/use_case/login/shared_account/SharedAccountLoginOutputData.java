package use_case.login.shared_account;

import use_case.login.LoginOutputData;

/**
 * The SharedAccountLoginOutputData class represents the output data of a shared account login operation.
 * It includes the identification of the user, the shared account ID, and the success status of the login attempt.
 *
 * @author Xile
 * @author Dana
 */
public class SharedAccountLoginOutputData extends LoginOutputData {

    /**
     * Constructs a SharedAccountLoginOutputData object with the specified identification, shared account ID, and success status.
     *
     * @param sharedAccountId the identification of the shared account
     * @param success        the success status of the login attempt
     */
    public SharedAccountLoginOutputData(String sharedAccountId, boolean success) {
        super(sharedAccountId, success);
    }
}

