package interface_adaptors;

import java.beans.PropertyChangeSupport;

/**
 * The HomePageViewModel class extends the ViewModel class and manages the labels for the home page view.
 * It provides getters for various labels used in the home page view and supports property change notifications.
 *
 * This class also manages the view transitions and supports new view options such as Shared Account Sign Up and Shared Account Log In.
 *
 * @author Dana
 * @author Eric
 */
public class HomePageViewModel extends ViewModel {

    private final String titleLabel = "Welcome to Accounting System";
    private final String signupButtonLabel = "User Account Sign Up";
    private final String sharedAccountSignupButtonLabel = "Shared Account Sign Up";
    private final String loginButtonLabel = "User Account Log In";
    private final String sharedAccountLoginButtonLabel = "Shared Account Log In";
    private final String exitButtonLabel = "Exit";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    // private HomePageState state = new HomePageState();

    /**
     * Constructs a HomePageViewModel object with the view name set to "Home Page".
     */
    public HomePageViewModel() {
        super("Home Page");
    }

    /**
     * Gets the title label.
     *
     * @return the title label
     */
    public String getTitleLabel() {
        return this.titleLabel;
    }

    /**
     * Gets the signup button label.
     *
     * @return the signup button label
     */
    public String getSignupButtonLabel() {
        return this.signupButtonLabel;
    }

    /**
     * Gets the shared account signup button label.
     *
     * @return the shared account signup button label
     */
    public String getSharedAccountSignupButtonLabel() {
        return this.sharedAccountSignupButtonLabel;
    }

    /**
     * Gets the login button label.
     *
     * @return the login button label
     */
    public String getLoginButtonLabel() {
        return this.loginButtonLabel;
    }

    /**
     * Gets the shared account login button label.
     *
     * @return the shared account login button label
     */
    public String getSharedAccountLoginButtonLabel() {
        return this.sharedAccountLoginButtonLabel;
    }

    /**
     * Gets the exit button label.
     *
     * @return the exit button label
     */
    public String getExitButtonLabel() {
        return this.exitButtonLabel;
    }
}
