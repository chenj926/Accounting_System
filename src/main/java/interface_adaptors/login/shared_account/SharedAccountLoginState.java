package interface_adaptors.login.shared_account;

import interface_adaptors.login.LoginState;

/**
 * The SharedAccountLoginState class extends LoginState to add properties
 * and methods specific to the shared account login process.
 * This class inherits the attributes and behavior of a standard login state
 * but is tailored to handle the unique aspects of logging into a shared account.
 *
 * @author Xile Chen
 */
public class SharedAccountLoginState extends LoginState {

    /**
     * Constructs a SharedAccountLoginState object with default values.
     * Initializes the shared account login state with default values for
     * identification, password, state error, and success message.
     */
    public SharedAccountLoginState() {
        super();
    }
}

