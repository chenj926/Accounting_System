package interface_adaptors.signup.user_account;

import interface_adaptors.signup.SignupState;

/**
 * The {@code UserAccountSignupState} class extends {@code SignupState} to include
 * additional information specific to user account signups, such as the username.
 * It manages the state of the user account signup process, including user details,
 * error messages, and success messages.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture,
 * ensuring that the state of the user account signup process is maintained and
 * can be easily accessed and modified by other components in the application.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen
 * </p>
 */
public class UserAccountSignupState extends SignupState {
    private String username;

    /**
     * Constructs a {@code UserAccountSignupState} object with default values.
     * This includes initializing the username to an empty string.
     */
    public UserAccountSignupState() {
        super();
        this.username = "";
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) { this.username = username;}

    /**
     * Resets the signup state to its default values, including resetting the username.
     */
    public void reset() {
        super.reset();
        this.setUsername("");
    }
}
