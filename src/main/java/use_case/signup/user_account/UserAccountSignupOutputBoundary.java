package use_case.signup.user_account;

import use_case.signup.SignupOutputBoundary;

/**
 * The UserAccountSignupOutputBoundary interface provides methods for preparing the view based on the outcome of a signup operation.
 * Implementations of this interface will handle the presentation logic for successful and failed signup attempts.
 *
 * @author Eric
 */
public interface UserAccountSignupOutputBoundary extends SignupOutputBoundary<UserAccountSignupOutputData> {
}