package interface_adaptors.signup.user_account;

import interface_adaptors.signup.SignupViewModel;

/**
 * The {@code UserAccountSignupViewModel} class extends {@code SignupViewModel}
 * to manage the state and labels specific to the user account signup view.
 * It provides access to the username label used in the signup process and
 * initializes the signup state.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture,
 * enabling interaction between the view and the underlying state management
 * for the user account signup process.
 * </p>
 *
 * <p>
 * <b>Author:</b> Jessica Chen
 * </p>
 */
public class UserAccountSignupViewModel extends SignupViewModel<UserAccountSignupState> {

    private final String USERNAME_LABEL = "Set user account username";

    /**
     * Constructs a {@code UserAccountSignupViewModel} object with the default view name.
     * Initializes the signup state to a new {@code UserAccountSignupState} instance.
     */
    public UserAccountSignupViewModel() {
        super("sign up");
        this.state = new UserAccountSignupState();
    }

    /**
     * Gets the username label for the signup view.
     *
     * @return the username label.
     */
    public String getUsernameLabel() { return this.USERNAME_LABEL; }
}