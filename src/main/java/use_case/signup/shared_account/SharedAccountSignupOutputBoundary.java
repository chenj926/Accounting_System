package use_case.signup.shared_account;

import use_case.signup.SignupOutputBoundary;

/**
 * The SharedAccountSignupOutputBoundary interface provides methods for preparing the view based on the outcome of a sharedAccount signup operation.
 * Implementations of this interface will handle the presentation logic for successful and failed signup attempts.
 *
 * @author Dana
 */

public interface SharedAccountSignupOutputBoundary extends SignupOutputBoundary<SharedAccountSignupOutputData> {
}
