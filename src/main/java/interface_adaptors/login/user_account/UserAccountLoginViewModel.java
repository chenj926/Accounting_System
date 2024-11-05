package interface_adaptors.login.user_account;


import interface_adaptors.login.LoginViewModel;

/**
 * The {@code UserAccountLoginViewModel} class extends {@code LoginViewModel} and manages the state
 * and labels for the user account login view. It provides specific labels for the user account login
 * process, such as title, identification, and password labels. This class also supports property
 * change notifications to update the view when the state changes.
 *
 * <p>This class is a key part of the user interface layer in a clean architecture, adhering to the
 * principles of separation of concerns and dependency inversion.</p>
 *
 * <p><b>Authors:</b> Jessica Chen, Eric Chen</p>
 */
public class UserAccountLoginViewModel extends LoginViewModel<UserAccountLoginState> {

    private final String titleLabel = "USER ACCOUNT LOGIN";
    private final String identificationLabel = "Enter user account ID";
    private final String passwordLabel = "Enter user account password";

    /**
     * Constructs a {@code UserAccountLoginViewModel} object with the view name set to "log in".
     * Initializes the state with a new {@code UserAccountLoginState} instance.
     */
    public UserAccountLoginViewModel() {
        super("log in");
        state = new UserAccountLoginState();
    }

}