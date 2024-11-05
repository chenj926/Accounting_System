package interface_adaptors.login;

/**
 * The {@code LoginState} class represents the state of the login process. This class
 * stores the user identification, password, any error messages, and success messages
 * related to the login attempt. It provides methods to access and modify these fields,
 * allowing for the management of login-related state information in a clean architecture.
 *
 * <p>This class is intended to be extended by more specific implementations of login
 * states that may include additional information or behavior.</p>
 *
 * <p><b>Author:</b> Jessica Chen</p>
 */
public abstract class LoginState {
    protected String identification;
    protected String password;
    protected String stateError;
    protected String successMsg;

    /**
     * Constructs a {@code LoginState} object with default values.
     * The identification and password are initialized to empty strings,
     * while the error and success messages are initialized to {@code null}.
     */
    public LoginState() {
        this.identification = "";
        this.password = "";
        this.stateError = null;
        this.successMsg = null;
    }

    /**
     * Gets the identification.
     *
     * @return the identification
     */
    public String getIdentification() {
        return this.identification;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the state error message.
     *
     * @return the state error message
     */
    public String getStateError() {
        return this.stateError;
    }

    /**
     * Gets the success message.
     *
     * @return the success message
     */
    public String getSuccessMsg() {
        return this.successMsg;
    }

    /**
     * Sets the identification.
     *
     * @param id the new identification
     */
    public void setIdentification(String id) {
        this.identification = id;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the state error message.
     *
     * @param err the new state error message
     */
    public void setStateError(String err) {
        this.stateError = err;
    }

    /**
     * Sets the success message.
     *
     * @param msg the new success message
     */
    public void setSuccessMsg(String msg) {
        this.successMsg = msg;
    }

}
