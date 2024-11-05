package interface_adaptors.login.user_account;

import interface_adaptors.login.LoginState;

/**
 * The {@code UserAccountLoginState} class represents the state of the login process
 * for a user account, including user identification, password, error messages,
 * and success messages. This class extends the base {@code LoginState} class
 * to capture the specific details related to user account login.
 *
 * <p>It provides a default constructor that initializes the state with default values.</p>
 *
 * <p><b>Authors:</b> Jessica Chen, Chi Fong Huang, Eric Chen</p>
 */
public class UserAccountLoginState extends LoginState {

    /**
     * Constructs a {@code UserAccountLoginState} object with default values.
     * Initializes the login state with empty identification, password, and null messages.
     */
    public UserAccountLoginState() {
        super();
    }
}
