package use_case.login.shared_account;

import use_case.login.LoginInputData;

/**
 * The {@code SharedAccountLoginInputData} class extends {@link LoginInputData} to include additional fields
 * specific to shared account login operations.
 * <p>
 * This class includes the shared account ID as part of the login input data, in addition to the usual identification
 * and password fields.
 * </p>
 *
 * @author Xile
 * @author Dana
 */
public class SharedAccountLoginInputData extends LoginInputData {

    /**
     * Constructs a SharedAccountLoginInputData object with the specified password,
     * identification, and shared account ID.
     *
     * @param sharedAccountId the shared account ID for the login
     * @param sharedPassword the shared account password for login
     */
    public SharedAccountLoginInputData(String sharedAccountId, String sharedPassword) {
        super(sharedAccountId, sharedPassword);
    }
}

