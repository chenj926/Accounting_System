package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The HomePageViewModel class extends the ViewModel class and manages the labels for the home page view.
 * It provides getters for various labels used in the home page view and supports property change notifications.
 *
 * @author Dana
 * @author Eric
 */
public class HomePageViewModel extends ViewModel{

    private final String titleLabel = "Welcome to Accounting System";
    private final String signupButtonLabel = "Sign up";
    private final String loginButtonLabel = "Login";
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
     * Gets the login button label.
     *
     * @return the login button label
     */
    public String getLoginButtonLabel() {
        return this.loginButtonLabel;
    }

    /**
     * Gets the exit button label.
     *
     * @return the exit button label
     */
    public String getExitButtonLabel() {
        return this.exitButtonLabel;
    }

    // public HomePageState getState() {
      //   return this.state;}

    // setter
    // public void setState(HomePageState state) { this.state = state; }




    //
    // public void firePropertyChanged() {
    //    support.firePropertyChange("state", null);}

    // public void addPropertyChangeListener(PropertyChangeListener listener) {
    //    support.addPropertyChangeListener(listener);
    }
