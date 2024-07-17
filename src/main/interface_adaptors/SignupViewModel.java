package interface_adaptors;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignupViewModel extends ViewModel {

    private final String TITLE_LABEL = "Sign Up View";
    private final String USERNAME_LABEL = "Choose username";
    private final String PASSWORD_LABEL = "Choose password";
    // do not need repeat_password for now
//    private final String REPEAT_PASSWORD_LABEL = "Enter password again";

    private final String SIGNUP_BUTTON_LABEL = "Sign up";
    private final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignupState state = new SignupState();

    public SignupViewModel() {
        super("sign up");
    }

    // label getters
    public String getTitleLabel() {return this.TITLE_LABEL; }
    public String getUsernameLabel() {return this.USERNAME_LABEL; }
    public String getPasswordLabel() {return this.PASSWORD_LABEL; }
    public String getSignupButtonLabel(){return this.SIGNUP_BUTTON_LABEL; }
    public String getCancelButtonLabel() {return this.CANCEL_BUTTON_LABEL; }

    // setters
    public void setState(SignupState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignupState getState() {
        return state;
    }
}