package use_case.login;

/**
 * The LoginOutputBoundary interface provides methods for preparing the view based on the outcome of a login operation.
 * Implementations of this interface will handle the presentation logic for successful and failed login attempts.
 *
 * @author Dana
 * @author Eric
 */
public interface UserLoginOutputBoundary extends LoginOutputBoundary<UserLoginOutputData> {
}