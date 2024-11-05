package interface_adaptors.login.shared_account;

import interface_adaptors.login.LoginViewModel;

/**
 * The SharedAccountLoginViewModel class extends LoginViewModel to manage the state and labels
 * for the shared account login view. It adds support for shared account-specific information,
 * providing customized labels and managing the login state for shared accounts.
 *
 * This class is part of the presentation layer in the Clean Architecture and is responsible
 * for holding the state of the shared account login view and notifying any listeners of changes.
 *
 * @author Xile Chen
 * @author Eric Chen
 */
public class SharedAccountLoginViewModel extends LoginViewModel<SharedAccountLoginState> {

    private final String titleLabel = "SHARED ACCOUNT LOGIN";
    private final String identificationLabel = "Enter shared account ID";
    private final String passwordLabel = "Enter shared account password";

    /**
     * Constructs a SharedAccountLoginViewModel object with the view name set to "shared account login".
     * Initializes the state with a new instance of SharedAccountLoginState.
     */
    public SharedAccountLoginViewModel() {
        super("shared account log in");
        state = new SharedAccountLoginState();
    }

    /**
     * Returns the title label for the shared account login view.
     *
     * @return the title label as a String
     */
    @Override
    public String getTitleLabel() {
        return this.titleLabel;
    }

    /**
     * Returns the identification label for the shared account login view.
     *
     * @return the identification label as a String
     */
    @Override
    public String getIdentificationLabel() {
        return this.identificationLabel;
    }

    /**
     * Returns the password label for the shared account login view.
     *
     * @return the password label as a String
     */
    @Override
    public String getPasswordLabel() {
        return this.passwordLabel;
    }



}
