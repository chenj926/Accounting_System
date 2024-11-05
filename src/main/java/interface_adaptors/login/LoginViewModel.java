package interface_adaptors.login;

import interface_adaptors.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The {@code LoginViewModel} class represents the view model for the login process.
 * It manages the state of the login UI components, including labels, buttons, and the current
 * login state, and provides methods to access these components. This class is designed to work
 * within a Clean Architecture framework, where it interacts with the view and business logic layers.
 *
 * <p>This abstract class should be extended by specific implementations that provide concrete
 * behavior for different types of login states.</p>
 *
 * @param <S> the type of {@code LoginState} that this view model manages
 *
 * <p><b>Author:</b> Jessica Chen</p>
 */
public abstract class LoginViewModel<S extends LoginState> extends ViewModel {
    protected final String titleLabel = "ACCOUNT LOGIN";
    protected final String identificationLabel = "Enter account ID";
    protected final String passwordLabel = "Enter account password";

    protected final String loginButtonLabel = "Login";
    protected final String cancelButtonLabel = "Cancel";

    protected S state;

    protected final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructs a {@code LoginViewModel} object with the view name set to the provided name.
     *
     * @param viewModelName the name of the view model
     */
    public LoginViewModel(String viewModelName) {
        super(viewModelName);
    }

    /**
     * Gets the title label for the login view.
     *
     * @return the title label
     */
    public String getTitleLabel(){
        return this.titleLabel;
    }

    /**
     * Gets the identification label.
     *
     * @return the identification label
     */
    public String getIdentificationLabel(){
        return this.identificationLabel;
    }

    /**
     * Gets the password label.
     *
     * @return the password label
     */
    public String getPasswordLabel(){
        return this.passwordLabel;
    }

    /**
     * Gets the login button label.
     *
     * @return the login button label
     */
    public String getLoginButtonLabel(){
        return this.loginButtonLabel;
    }

    /**
     * Gets the cancel button label.
     *
     * @return the cancel button label
     */
    public String getCancelButtonLabel(){
        return this.cancelButtonLabel;
    }

    /**
     * Gets the current login state.
     *
     * @return the current login state
     */
    public S getState() {
        return this.state;
    }

    /**
     * Sets the current login state.
     *
     * @param state the new login state
     */
    public void setState(S state) {
        this.state = state;
    }

    /**
     * Notifies listeners that the shared account login state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to the listener list.
     *
     * @param listener the {@code PropertyChangeListener} to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
