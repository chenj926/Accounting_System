package interface_adaptors.signup.shared_account;

import interface_adaptors.signup.SignupViewModel;

/**
 * The {@code SharedAccountSignupViewModel} class extends {@code SignupViewModel}
 * to add properties and methods specific to the shared account signup process.
 * It provides additional labels and state management tailored to the needs of
 * the shared account signup view.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture,
 * managing the interaction between the shared account signup view and the underlying
 * state management. It ensures that the view is correctly updated based on the current
 * state of the signup process.
 * </p>
 *
 * <p>
 * <b>Author:</b> Xile Chen
 * </p>
 */
public class SharedAccountSignupViewModel extends SignupViewModel<SharedAccountSignupState> {

    private final String TITLE_LABEL = "Shared Account Sign Up";
    private final String ID_LABEL = "Set shared account id";
    private final String PASSWORD_LABEL = "Set shared account password";

    /**
     * Constructs a {@code SharedAccountSignupViewModel} object with the view name set to "shared account sign up".
     * Initializes the signup state to a new {@code SharedAccountSignupState} instance.
     */
    public SharedAccountSignupViewModel() {
        super("shared account sign up");
        this.state = new SharedAccountSignupState();
    }

    /**
     * Gets the title label for the shared account signup view.
     *
     * @return the title label.
     */
    @Override
    public String getTitleLabel() { return this.TITLE_LABEL; }

    /**
     * Gets the ID label for the shared account signup view.
     *
     * @return the ID label.
     */
    @Override
    public String getID_LABEL() { return this.ID_LABEL; }

    /**
     * Gets the password label for the shared account signup view.
     *
     * @return the password label.
     */
    @Override
    public String getPasswordLabel() { return this.PASSWORD_LABEL; }


}
