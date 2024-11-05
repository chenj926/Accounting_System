package interface_adaptors.signup;

/**
 * The {@code SignupState} class represents the state of the signup process,
 * encapsulating user details, error messages, and success messages. This class
 * provides the foundation for managing the state during the signup process, ensuring
 * that the application maintains a consistent state throughout the operation.
 * <p>
 * This class is part of the presentation layer in the Clean Architecture, separating
 * the business logic from the view logic. It abstracts the state management, allowing
 * the underlying logic to be independent of how the state is represented and manipulated.
 * </p>
 *
 * <p>
 * <b>Author:</b> Chi Feng Huang, Jessica Chen, Eric Chen
 * </p>
 */
public abstract class SignupState {
    protected String identification;
    protected String password;

    protected String stateError;
    protected String successMsg;

    /**
     * Constructs a {@code SignupState} object with default values.
     * The default values ensure that the signup state starts with no user details or messages.
     */
    public SignupState() {
        this.identification = "";
        this.password = "";
        this.stateError = null;
        this.successMsg = null;
    }

    /**
     * Gets the identification associated with the signup state.
     *
     * @return the identification.
     */
    public String getIdentification() { return this.identification; }

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
    public String getStateError() { return this.stateError; }

    /**
     * Gets the success message.
     *
     * @return the success message
     */
    public String getSuccessMsg() { return this.successMsg; }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the identification.
     *
     * @param identification the new identification
     */
    public void setIdentification(String identification) { this.identification = identification; }


    /**
     * Sets the state error message.
     *
     * @param err the new state error message
     */
    public void setStateError(String err) { this.stateError = err; }

    /**
     * Sets the success message.
     *
     * @param msg the new success message
     */
    public void setSuccessMsg(String msg) { this.successMsg = msg; }

    /**
     * Checks if the signup state is valid, meaning a success message is present.
     *
     * @return {@code true} if the signup state is valid, {@code false} otherwise.
     */
    public boolean isValid() {
        return this.successMsg != null;
    }

    /**
     * Resets the signup state to its default values.
     * This method clears all user details, error messages, and success messages,
     * effectively returning the state to its initial configuration.
     */
    public void reset() {
        this.identification = "";
        this.setPassword("");
        this.setStateError(null);
        this.setSuccessMsg(null);
    }
}